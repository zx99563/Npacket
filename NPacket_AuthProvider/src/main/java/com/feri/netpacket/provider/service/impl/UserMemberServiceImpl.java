package com.feri.netpacket.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.feri.common.dto.UserLoginDto;
import com.feri.common.redis.JedisUtil;
import com.feri.common.redis.RedisKey;
import com.feri.common.util.EncryptUtil;
import com.feri.common.util.JwtUtil;
import com.feri.common.vo.LoginToken;
import com.feri.common.vo.R;
import com.feri.netpacket.entity.user.UserMember;
import com.feri.netpacket.provider.dao.UserMemberDao;
import com.feri.netpacket.provider.service.UserMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class UserMemberServiceImpl implements UserMemberService {

    @Autowired
    private UserMemberDao dao;
    //注入我们指定的属性
    @Value("${npacket.passkey}")
    private String key;

    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public R check(String phone) {
        UserMember member=dao.selectByPhone(phone);
        if(member==null){
            return R.setOK();//手机号可用
        }else{
            if(member.getFlag()>1){
                //改手机号 被禁用
                return R.setERROR("改号已被禁用，请合理使用！");
            }
        }
        return R.setERROR("改号已被注册");
    }

    @Override
    public R register(UserMember member) {
        //注册新用户
        if(check(member.getPhone()).getCode()==200) {
            //密码改为密文
            member.setPassword(EncryptUtil.aesenc(key, member.getPassword()));
            //实现注册
            if (dao.insert(member) > 0) {
                return R.setOK();
            } else {
                return R.setERROR();
            }
        }else {
            return R.setERROR("改号已被注册！");
        }
    }

    //限定次数登陆 一个号允许在线3个 前后端 令牌 Token(JWT)
    @Override
    public R login(UserLoginDto loginDto) {
        //登陆
        UserMember member=dao.selectByPhone(loginDto.getPhone());
        //校验 账号的有效性
        if(member!=null && member.getFlag()==1){
            //密码校验
            if(member.getPassword().equals(EncryptUtil.aesenc(key,loginDto.getPsw()))){
                //登陆成功
                //校验目前在线了几个 最多3个
                Set<String> keys=jedisUtil.keys(RedisKey.LOGIN_PHONE+member.getPhone()+"*");
                if(keys.size()>2){
                    //需要挤掉一个在线用户 先来后到 挤掉最早的一个
                    String key=minKey(keys);
                    String tk=jedisUtil.get(key);
                    //删除掉要被挤掉的key
                    jedisUtil.del(key,RedisKey.LOGIN_TOKEN+tk);
                    //记录被挤掉的账号信息
                    jedisUtil.hset(RedisKey.LOGIN_EDGE,tk,member.getPhone());
                }
                //生成令牌
                LoginToken token=new LoginToken();
                token.setId(member.getId());
                token.setPhone(member.getPhone());
                String t= JwtUtil.createJWT(JSON.toJSONString(token));
                //令牌存储 Redis 有效期
                //记录手机号和对应的令牌
                jedisUtil.setex(RedisKey.LOGIN_PHONE+member.getPhone()+":"+System.currentTimeMillis(),RedisKey.LOGIN_TIME,t);
                //记录令牌和对应的用户信息
                jedisUtil.setex(RedisKey.LOGIN_TOKEN+t,RedisKey.LOGIN_TIME,JSON.toJSONString(member));
                return R.setOK(t);
            }
        }
        return R.setERROR();
    }
    //@Async //修饰需要异步执行的方法 多线程执行
    @Override
    public R checkToken(String token) {
        if(token!=null && token.length()>0){
            if(jedisUtil.exists(RedisKey.LOGIN_TOKEN+token)){
                return R.setOK();
            }else if(jedisUtil.hexists(RedisKey.LOGIN_EDGE,token)){
                //删除信息
                jedisUtil.hdel(RedisKey.LOGIN_EDGE,token);
                //无效的原因 被挤掉了
                return R.setERROR("亲，该改密码，因为在线太多，被挤掉了！");
            }
        }
        return R.setERROR("令牌无效");
    }

    private String minKey(Set<String> set){
        return set.stream().sorted().findFirst().get();
    }

}

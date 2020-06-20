package com.feri.netpacket.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feri.common.redis.JedisUtil;
import com.feri.common.redis.RedisKey;
import com.feri.common.vo.R;
import com.feri.netpacket.entity.user.UserMember;
import com.feri.netpacket.entity.user.UserMemberDetail;
import com.feri.netpacket.provider.dao.UserMemberDetailDao;
import com.feri.netpacket.provider.service.UserMemberDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-08 14:19
 */
@Service
public class UserMemberDetailServiceImpl implements UserMemberDetailService {

    //@Resource(name = "") //可以通过类型，还可以通过id注入 适用于一个类型用于多个对象
    //    @Qualifier(value = "")
    @Autowired //只能通过类型注入
    private UserMemberDetailDao detailDao;
    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public R getDetail(String token) {
        if(jedisUtil.exists(RedisKey.LOGIN_TOKEN+token)){
            //获取对应的用户id
            UserMember member= JSON.parseObject(jedisUtil.get(RedisKey.LOGIN_TOKEN+token),UserMember.class);
            return R.setOK(detailDao.selectOne(new QueryWrapper<UserMemberDetail>().eq("mid",member.getId())));
        }
        return R.setERROR("令牌失效，请重新登陆！");
    }

    @Override
    public R changeDetail(UserMemberDetail detail) {
        if(detailDao.updateById(detail)>0){
            return R.setOK();
        }else {
            return R.setERROR();
        }
    }
}

package com.feri.netpacket.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.feri.common.dto.SkillGoodsDto;
import com.feri.common.dto.SkillUrlDto;
import com.feri.common.redis.RedisKey;
import com.feri.common.type.OrderStatus;
import com.feri.common.util.EncryptUtil;
import com.feri.common.vo.R;
import com.feri.netpacket.entity.skill.SkillGoods;
import com.feri.netpacket.entity.skill.SkillOrder;
import com.feri.netpacket.entity.user.UserMember;
import com.feri.netpacket.provider.config.RabbitConfig;
import com.feri.netpacket.provider.dao.SkillGoodsDao;
import com.feri.netpacket.provider.service.SkillService;
import com.feri.netpacket.provider.util.RedissonUtil;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-16 09:33
 */
@Service
public class SkillServiceImpl implements SkillService {
    @Value("")
    private int httpLimiter;
    private RateLimiter limiter;

    @Value("")
    private String key1;

    @Value("")
    private String key2;

    public SkillServiceImpl(){
        //实例化
        limiter=RateLimiter.create(httpLimiter);
    }
    @Autowired
    private RedissonUtil redissonUtil;
    @Autowired
    private SkillGoodsDao goodsDao;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public R createSkillUrl(SkillGoodsDto dto) {
        //查询数据库 获取秒杀商品信息
        SkillGoods sg=goodsDao.getById(dto.getGid());
        //校验开始时间和结束时间
        if(System.currentTimeMillis()<sg.getBtime().getTime()){
            return R.setERROR("秒杀还未开始");
        }
        if(System.currentTimeMillis()>sg.getEtime().getTime()){
            return R.setERROR("秒杀已结束！");
        }
        //转换用户对象
        if(redissonUtil.checkKey(RedisKey.LOGIN_TOKEN+dto.getToken())) {
            //获取 秒杀路径信息
            //加密规则- 根据商品id和时间戳 采用AES加密  存储到Redis
            String gurl;
            if(redissonUtil.checkHKey(RedisKey.SKILL_GOODSURL,dto.getGid()+"")){
                gurl=redissonUtil.getHashV(RedisKey.SKILL_GOODSURL,dto.getGid()+"");
            }else{
                gurl= EncryptUtil.aesenc(key1,dto.getGid()+":"+System.currentTimeMillis());
                redissonUtil.hadd(RedisKey.SKILL_GOODSURL,dto.getGid()+"",gurl);
            }
            UserMember member = JSON.parseObject(redissonUtil.get(RedisKey.LOGIN_TOKEN + dto.getToken()), UserMember.class);
            //用户id 和密文 组成JSON 在进行二次加密 密文 作为最终返回结果
            SkillUrlDto urlDto=new SkillUrlDto();
            urlDto.setKey(gurl);
            urlDto.setUid(member.getId());
            String u=EncryptUtil.aesenc(key2,JSON.toJSONString(urlDto));
            return R.setOK(u);
        }else {
            return R.setERROR("登录已失效，请重新登录！");
        }
    }

    @Override
    public R skill(String token, int gid) {
        limiter.acquire();
        //查询数据库 获取秒杀商品信息
        SkillGoods sg=goodsDao.getById(gid);
        //校验开始时间和结束时间
        if(System.currentTimeMillis()<sg.getBtime().getTime()){
            return R.setERROR("秒杀还未开始");
        }
        if(System.currentTimeMillis()>sg.getEtime().getTime()){
            return R.setERROR("秒杀已结束！");
        }
        //转换用户对象
        if(redissonUtil.checkKey(RedisKey.LOGIN_TOKEN+token)) {
            UserMember member = JSON.parseObject(redissonUtil.get(RedisKey.LOGIN_TOKEN + token), UserMember.class);
            //校验用户是否为黑名单
            if(redissonUtil.checkSet(RedisKey.SKILL_HMD,member.getId())){
                return R.setERROR("亲，你已经被拉黑");
            }
            //校验用户是否已经购买过此商品
            if(redissonUtil.checkHKey(RedisKey.SKILL_ORDER,member.getId()+":"+gid)){
                //当前用户 已经购买过此商品
                return R.setERROR("亲，你已经购买过该秒杀商品了");
            }
            try{
                redissonUtil.lock(RedisKey.SKILL_LOCKKEY);
                //校验库存
                int s=redissonUtil.getHashVInt(RedisKey.SKILL_GOODS,gid+"");
                if(s>0){
                    //生成订单
                    //库存有 可以下单  --Redis --MQ-- -- Mysql
                    SkillOrder order=new SkillOrder();
                    order.setCtime(new Date());
                    order.setGid(gid);
                    order.setTprice(sg.getSprice());
                    order.setStatus(OrderStatus.未付款.getVal());
                    String json=JSON.toJSONString(order);
                    redissonUtil.hadd(RedisKey.SKILL_ORDER,member.getId()+":"+gid,json);
                    //更改Redis库存
                    redissonUtil.hadd(RedisKey.SKILL_GOODS,gid+"",s-1);
                    //基于RabbitMQ发送下单消息
                    rabbitTemplate.convertAndSend(RabbitConfig.fanoutOrder,null,json);
                    return R.setOK();
                }else {
                    return R.setERROR("库存不足");
                }
            }finally {
                redissonUtil.unlock(RedisKey.SKILL_LOCKKEY);
            }
        }else {
            return R.setERROR("登录失效");
        }
    }

    @Override
    public R skillV2(String path) {
        limiter.acquire();
        try {
            SkillUrlDto urlDto = JSON.parseObject(EncryptUtil.aesdec(key2, path), SkillUrlDto.class);
            String ids=EncryptUtil.aesdec(key1,urlDto.getKey());
            int gid=Integer.parseInt(ids.split(":")[0]);
            //查询数据库 获取秒杀商品信息
            SkillGoods sg=goodsDao.getById(gid);
            if(sg!=null) {
                //校验开始时间和结束时间
                if (System.currentTimeMillis() < sg.getBtime().getTime()) {
                    return R.setERROR("秒杀还未开始");
                }
                if (System.currentTimeMillis() > sg.getEtime().getTime()) {
                    return R.setERROR("秒杀已结束！");
                }
                //校验用户是否为黑名单
                if (redissonUtil.checkSet(RedisKey.SKILL_HMD, urlDto.getUid())) {
                    return R.setERROR("亲，你已经被拉黑");
                }
                //校验用户是否已经购买过此商品
                if (redissonUtil.checkHKey(RedisKey.SKILL_ORDER, urlDto.getUid() + ":" + gid)) {
                    //当前用户 已经购买过此商品
                    return R.setERROR("亲，你已经购买过该秒杀商品了");
                }
                try {
                    redissonUtil.lock(RedisKey.SKILL_LOCKKEY);
                    //校验库存
                    int s = redissonUtil.getHashVInt(RedisKey.SKILL_GOODS, gid + "");
                    if (s > 0) {
                        //生成订单
                        //库存有 可以下单  --Redis --MQ-- -- Mysql
                        SkillOrder order = new SkillOrder();
                        order.setCtime(new Date());
                        order.setGid(gid);
                        order.setTprice(sg.getSprice());
                        order.setStatus(OrderStatus.未付款.getVal());
                        String json = JSON.toJSONString(order);
                        redissonUtil.hadd(RedisKey.SKILL_ORDER, urlDto.getUid() + ":" + gid, json);
                        //更改Redis库存
                        redissonUtil.hadd(RedisKey.SKILL_GOODS, gid + "", s - 1);
                        //基于RabbitMQ发送下单消息
                        rabbitTemplate.convertAndSend(RabbitConfig.fanoutOrder, null, json);

                        return R.setOK();
                    } else {
                        return R.setERROR("库存不足");
                    }
                } finally {
                    redissonUtil.unlock(RedisKey.SKILL_LOCKKEY);
                }
            }else {
                return R.setERROR("亲，该商品不是秒杀商品");
            }
        } catch (Exception e) {
                return R.setERROR("亲，请求无效！");
        }
    }
}

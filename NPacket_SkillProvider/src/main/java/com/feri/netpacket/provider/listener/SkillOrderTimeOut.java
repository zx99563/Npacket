package com.feri.netpacket.provider.listener;

import com.alibaba.fastjson.JSON;
import com.feri.common.redis.RedisKey;
import com.feri.common.type.OrderStatus;
import com.feri.netpacket.entity.skill.SkillOrder;
import com.feri.netpacket.provider.dao.SkillOrderDao;
import com.feri.netpacket.provider.util.RedissonUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-16 18:26
 */
@Component
@RabbitListener(queues = "Npacket:skill:timeoutorder")
public class SkillOrderTimeOut {

    @Autowired
    private SkillOrderDao orderDao;
    @Autowired
    private RedissonUtil redissonUtil;

    @RabbitHandler
    public void handler(String msg){
        //将新增的秒杀订单添加到数据库
        SkillOrder order= JSON.parseObject(msg,SkillOrder.class);
        //查询数据库
        SkillOrder mysqlOrder=orderDao.selectById(order.getId());
        if(mysqlOrder.getStatus()== OrderStatus.未付款.getVal()){
            //订单 超时 10分钟还未付款 自动变更为超时订单
            //更改订单状态
            orderDao.changeStatus(OrderStatus.超时订单.getVal());
            //释放库存
            int c=redissonUtil.getHashVInt(RedisKey.SKILL_GOODS,order.getGid()+"");
            redissonUtil.hadd(RedisKey.SKILL_GOODS,order.getGid()+"",c+1);
        }
    }
}

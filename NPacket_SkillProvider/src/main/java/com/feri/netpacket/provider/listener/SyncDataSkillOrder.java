package com.feri.netpacket.provider.listener;

import com.alibaba.fastjson.JSON;
import com.feri.netpacket.entity.skill.SkillOrder;
import com.feri.netpacket.provider.dao.SkillOrderDao;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-16 18:22
 */
@Component
@RabbitListener(queues = "Npacket:skill:order")
public class SyncDataSkillOrder {
    @Autowired
    private SkillOrderDao orderDao;

    @RabbitHandler
    public void handler(String msg){
        //将新增的秒杀订单添加到数据库
        SkillOrder order= JSON.parseObject(msg,SkillOrder.class);
        orderDao.insert(order);
        //生成订单流水

    }
}

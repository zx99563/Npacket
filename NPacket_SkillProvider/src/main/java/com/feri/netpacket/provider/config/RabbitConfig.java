package com.feri.netpacket.provider.config;

import com.feri.common.config.SkillConfig;
import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-16 17:44
 */
@Configuration
public class RabbitConfig {
    //定义队列 存储秒杀订单 目的 同步Redis秒杀订单到Mysql 有消费者
    public static final String qsyncName="Npacket:skill:order";

    @Bean
    public Queue createQn(){
        return new Queue(qsyncName);
    }

    //基于RabbitMQ 实现延迟队列 用来实现超时订单处理
    //延迟队列 内部消息 需要设置有效期  无消费者
    public static final String delayqname="Npacket:skill:delayorder";
    //死信队列 存储死信消息  有消费者
    public static final String deadqname="Npacket:skill:timeoutorder";

    //需要交换器 1：转发消息到2个队列中 2：死信交换器 专门用来转发死信消息
    public static final String fanoutOrder="Npacket:order";
    public  static final String deadExchange="Npacket:dead";
    //设置死信转发路由
    public static final String deadorder="skillorderttl";

    @Bean
    public Queue createDelay(){
        Map<String,Object> params=new HashMap<>();
        //设置内部消息的有效期
        params.put("x-message-ttl", SkillConfig.SKILL_OTIME*1000);
        //设置死信交换器
        params.put("x-dead-letter-exchange",deadExchange);
        //设置死信消息对应的路由
        params.put("x-dead-routing-key",deadorder);
        //创建具备延迟特性的队列信息
        return QueueBuilder.durable(delayqname).withArguments(params).build();
    }
    @Bean
    public Queue createDead(){
        return new Queue(deadqname);
    }

    //创建交换器
    @Bean
    public FanoutExchange createFX(){
        return new FanoutExchange(fanoutOrder);
    }
    @Bean
    public DirectExchange createDx(){
        return new DirectExchange(deadExchange);
    }

    //绑定队列 转发交换器 实现订单消息转发到：延迟队列和订单队列
    @Bean
    public Binding createOQ1(){
        return BindingBuilder.bind(createQn()).to(createFX());
    }
    @Bean
    public Binding createOQ2(){
        return BindingBuilder.bind(createDelay()).to(createFX());
    }
    //绑定 死信交换器 到死信队列 并指定路由名称
    @Bean
    public Binding createOD3(){
        return BindingBuilder.bind(createDead()).to(createDx()).with(deadorder);
    }


}

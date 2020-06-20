package com.feri.netpacket.provider.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-06 14:36
 */
@Configuration
public class RabbitMQConfig {
    //声明队列名称 存储短信验证码
    public static String smscodeQname="npacket.smscode";
    @Bean
    public Queue createQueue(){
        return new Queue(smscodeQname);
    }
}
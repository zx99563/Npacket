package com.feri.netpacket.provider.config;

import com.feri.netpacket.provider.util.RedissonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-16
 * 10:05
 */
@Configuration
public class RedisConfig {
    @Value("")
    private String host;
    @Value("")
    private String pass;
    @Value("")
    private int port;
    @Bean
    public RedissonUtil createRU(){
       return new RedissonUtil(host,port,pass);
    }
}

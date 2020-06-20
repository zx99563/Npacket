package com.feri.netpacket.provider.config;

import com.feri.common.redis.JedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-05 10:19
 */
@Configuration
public class RedisConfig {
    @Value("${npacket.redis.host}")
    private String host;
    @Value("${npacket.redis.port}")
    private int port;
    @Value("${npacket.redis.psw}")
    private String psw;
    @Bean
    public JedisUtil createJU(){
        return new JedisUtil(host,port,psw);
    }
}

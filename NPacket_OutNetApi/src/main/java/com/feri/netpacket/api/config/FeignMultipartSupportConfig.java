package com.feri.netpacket.api.config;

import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Bean;
import feign.codec.Encoder;
import org.springframework.context.annotation.Configuration;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-09 13:54
 */
@Configuration
public class FeignMultipartSupportConfig {
    @Bean
    public Encoder multipartFormEncoder() {
        return new SpringFormEncoder();
    }
    @Bean
    public feign.Logger.Level multipartLoggerLevel() {
        return feign.Logger.Level.FULL;
    }
}
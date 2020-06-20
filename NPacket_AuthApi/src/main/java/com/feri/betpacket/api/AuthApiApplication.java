package com.feri.betpacket.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient //发现服务
@EnableFeignClients //启用Feign
public class AuthApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApiApplication.class,args);
    }
}
package com.feri.netpacket.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

//@EnableAsync //开启异步
@SpringBootApplication
@EnableDiscoveryClient //注册和发现服务
@MapperScan(basePackages = "com.feri.netpacket.provider.dao") //扫描Mybatis 的接口所在的包
public class AuthProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthProviderApplication.class,args);
    }
}
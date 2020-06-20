package com.feri.netpacket.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-08 14:31
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class UserApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApiApplication.class,args);
    }
}

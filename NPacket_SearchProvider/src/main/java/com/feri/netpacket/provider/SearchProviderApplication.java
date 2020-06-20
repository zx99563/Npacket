package com.feri.netpacket.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-11 14:10
 */
@SpringBootApplication
@EnableScheduling //启用定时任务 Spring-Task
@EntityScan(basePackages = "com.feri.netpacket.provider.entity")
@EnableDiscoveryClient
public class SearchProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchProviderApplication.class,args);
    }
}

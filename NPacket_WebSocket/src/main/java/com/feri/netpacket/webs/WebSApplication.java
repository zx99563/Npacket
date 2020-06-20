package com.feri.netpacket.webs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-17 16:43
 */
@SpringBootApplication
public class WebSApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebSApplication.class,args);
    }
}

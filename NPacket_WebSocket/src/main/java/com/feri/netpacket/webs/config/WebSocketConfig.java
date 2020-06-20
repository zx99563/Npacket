package com.feri.netpacket.webs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-17 17:02
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter createSEE(){
        return new ServerEndpointExporter();
    }
}

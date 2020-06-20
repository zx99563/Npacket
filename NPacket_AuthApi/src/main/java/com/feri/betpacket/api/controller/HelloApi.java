package com.feri.betpacket.api.controller;

import com.feri.betpacket.api.service.HelloService;
import com.feri.common.vo.R;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloApi {
    @Autowired
    private HelloService service;

    @GetMapping("api/hello.do")
    public R hi(){
        return service.hi();
    }
}

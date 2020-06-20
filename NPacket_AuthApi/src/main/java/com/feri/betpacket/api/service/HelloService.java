package com.feri.betpacket.api.service;

import com.feri.common.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//声明 指定的服务名称
@FeignClient("AuthProvider")
public interface HelloService {
    @GetMapping("provider/hello.do")
    R hi();
}

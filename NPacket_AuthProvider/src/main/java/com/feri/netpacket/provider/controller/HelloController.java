package com.feri.netpacket.provider.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.feri.common.vo.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    //测试方法 接口
    @GetMapping("provider/hello.do")
    public R hello(){
        return R.setOK();
    }
}

package com.feri.netpacket.provider.controller;


import com.feri.common.vo.R;
import com.feri.common.vo.SmsCode;
import com.feri.netpacket.provider.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SmsController {

    @Autowired
    private SmsService smsService;

    //发送短信验证码
    @PostMapping("/smsprovider/sendcode.do")
    public R send(@RequestParam String phone){
        return smsService.sendCode(phone);
    }

    //校验验证码
    @GetMapping("/smsprovider/checkcode.do")
    public R checkCode(@RequestBody SmsCode code){
        return smsService.checkCode(code);
    }

}

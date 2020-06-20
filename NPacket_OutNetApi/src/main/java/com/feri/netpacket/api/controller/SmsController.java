package com.feri.netpacket.api.controller;

import com.feri.common.vo.R;
import com.feri.common.vo.SmsCode;
import com.feri.netpacket.api.service.SmsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-05 14:43
 */
@Api
@RestController
public class SmsController {
    @Autowired
    private SmsService smsService;


    //发送短信验证码
    @PostMapping("api/sms/sendcode.do")
    public R send(@RequestParam String phone){
        return smsService.send(phone);
    }

    //校验验证码
    @GetMapping("api/sms/checkcode.do")
    public R checkCode(@RequestBody SmsCode code){
        return smsService.checkCode(code);
    }

}


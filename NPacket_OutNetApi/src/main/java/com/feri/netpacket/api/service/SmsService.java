package com.feri.netpacket.api.service;

import com.feri.common.vo.R;
import com.feri.common.vo.SmsCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-05 14:41
 */
@FeignClient("OutNetProvider")
public interface SmsService {
    //发送短信验证码
    @PostMapping("/smsprovider/sendcode.do")
    R send(@RequestParam String phone);

    //校验验证码
    @GetMapping("/smsprovider/checkcode.do")
    R checkCode(@RequestBody SmsCode code);

}

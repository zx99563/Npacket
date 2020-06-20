package com.feri.betpacket.api.controller;

import com.feri.betpacket.api.service.UserMemberService;
import com.feri.common.dto.UserLoginDto;
import com.feri.common.vo.R;
import com.feri.netpacket.entity.user.UserMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserMemberApi {
    @Autowired
    private UserMemberService service;

    //注册新用户
    @PostMapping("/authapi/userregister.do")
    public R add(@RequestBody UserMember member){
        return service.add(member);
    }

    //验证手机号是否存在

    //登陆 限定次数
    @GetMapping("authapi/checkphone.do")
    public R check(@RequestParam String phone){
        return service.checkPhone(phone);
    }

    //登陆 限定次数
    @PostMapping("authapi/userlogin.do")
    public R login(@RequestBody UserLoginDto loginDto){
        return service.login(loginDto);
    }

    //校验令牌是否有效
    @GetMapping("authapi/checktoken.do")
    public R checkToken(@RequestParam String token){
        return service.checkToken(token);
    }
}

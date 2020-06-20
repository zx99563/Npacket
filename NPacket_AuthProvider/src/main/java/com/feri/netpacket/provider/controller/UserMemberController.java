package com.feri.netpacket.provider.controller;

import com.feri.common.dto.UserLoginDto;
import com.feri.common.vo.R;
import com.feri.netpacket.entity.user.UserMember;
import com.feri.netpacket.provider.service.UserMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserMemberController {
    @Autowired
    private UserMemberService service;


    //验证手机号是否存在
    //登陆 限定次数
    @GetMapping("authprovider/checkphone.do")
    public R checkPhone(@RequestParam String phone){
        return service.check(phone);
    }


    //注册新用户
    @PostMapping("/authprovider/userregister.do")
    public R add(@RequestBody UserMember member){
        return service.register(member);
    }

    //登陆 限定次数
    @PostMapping("authprovider/userlogin.do")
    public R login(@RequestBody UserLoginDto loginDto){
        return service.login(loginDto);
    }

    //校验令牌是否有效
    @GetMapping("authprovider/checktoken.do")
    public R checkToken(@RequestParam String token){
        return service.checkToken(token);
    }
}

package com.feri.betpacket.api.service;

import com.feri.common.dto.UserLoginDto;
import com.feri.common.vo.R;
import com.feri.netpacket.entity.user.UserMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("AuthProvider")
public interface UserMemberService {
    // //注册新用户
    @PostMapping("/authprovider/userregister.do")
    R add(@RequestBody UserMember member);
    //验证手机号是否存在
    @GetMapping("authprovider/checkphone.do")
    R checkPhone(@RequestParam String phone);
    //登陆 限定次数
    @PostMapping("authprovider/userlogin.do")
    R login(@RequestBody UserLoginDto loginDto);
    //校验令牌是否有效
    @GetMapping("authprovider/checktoken.do")
    R checkToken(@RequestParam String token);
}


package com.feri.netpacket.provider.service;

import com.feri.common.dto.UserLoginDto;
import com.feri.common.vo.R;
import com.feri.netpacket.entity.user.UserMember;

public interface UserMemberService {
    //校验手机号是否存在
    R check(String phone);
    //注册新用户
    R register(UserMember member);
    //密码找回

    //登陆
    R login(UserLoginDto loginDto);
    //校验令牌
    R checkToken(String token);
    //修改密码


}

package com.feri.netpacket.provider.dao;

import com.feri.netpacket.entity.user.UserMember;

public interface UserMemberDao {
    int insert(UserMember member);
    UserMember selectByPhone(String phone);
}

package com.feri.netpacket.provider.dao;

import com.feri.netpacket.entity.user.UserMemberLogin;
import org.apache.ibatis.annotations.Insert;

public interface UserMemberLoginDao {

    int insert(UserMemberLogin login);
}

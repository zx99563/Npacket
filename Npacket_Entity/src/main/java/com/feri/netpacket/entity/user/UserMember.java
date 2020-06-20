package com.feri.netpacket.entity.user;

import lombok.Data;

import java.util.Date;

/**
 *  用户信息
 * @author Feri 2020-06-04
 */
@Data
public class UserMember {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 手机号
     */
    private String phone;
    /**
     * 密码
     * */
    private String password;

    /**
     * 标记位*/
    private int flag;

    /**
     * 创建时间
     */
    private Date ctime;

    public UserMember() {
    }
}

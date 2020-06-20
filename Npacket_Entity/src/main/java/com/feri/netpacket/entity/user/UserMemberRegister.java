package com.feri.netpacket.entity.user;

import lombok.Data;

import java.util.Date;

/**
 *  会员注册记录
 * @author Feri 2020-06-04
 */
@Data
public class UserMemberRegister {
    /**
     * id
     */
    private Long id;

    /**
     * mid
     */
    private Long mid;

    /**
     * ctime
     */
    private Date ctime;

    /**
     * ip
     */
    private String ip;

    /**
     * province
     */
    private String province;

    /**
     * city
     */
    private String city;

    /**
     * 注册类型：1->pc；2->android; 3-> ios ; 4->小程序
     */
    private Integer registerType;

    /**
     * 注册的设备详细信息
     */
    private String detail;

    public UserMemberRegister() {
    }
}

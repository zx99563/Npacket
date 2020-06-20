package com.feri.netpacket.entity.user;

import java.util.Date;
import lombok.Data;

/**
 * <p>
 * 会员详情表
 * </p>
 *
 * @author Feri(邢朋辉)
 * @since 2020-06-08
 */
@Data
public class UserMemberDetail {

    private Long id;

    private Long mid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 头像
     */
    private Integer icon;

    /**
     * 性别：0->未知；1->男；2->女
     */
    private Integer gender;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 职业
     */
    private String job;

    /**
     * 个性签名
     */
    private String personalized_signature;

    /**
     * 用户来源
     */
    private Integer source_type;
}

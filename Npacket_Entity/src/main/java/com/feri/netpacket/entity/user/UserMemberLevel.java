package com.feri.netpacket.entity.user;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 会员等级表
 * </p>
 *
 * @author Feri(邢朋辉)
 * @since 2020-06-08
 */
@Data
public class UserMemberLevel {

    private Long id;

    private Long mid;

    /**
     * 会员等级 等级详情表
     */
    private Integer level;

    /**
     * 成长值
     */
    private Integer growth;
}
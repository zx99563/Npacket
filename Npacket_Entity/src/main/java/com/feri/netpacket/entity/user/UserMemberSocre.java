package com.feri.netpacket.entity.user;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 会员积分表
 * </p>
 *
 * @author Feri(邢朋辉)
 * @since 2020-06-08
 */
@Data
public class UserMemberSocre {

    private Long id;

    private Long mid;

    /**
     * 可用积分
     */
    private Integer integration;

    /**
     * 历史积分数量
     */
    private Integer history_integration;

}

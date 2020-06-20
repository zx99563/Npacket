package com.feri.netpacket.entity.user;

import java.util.Date;
import lombok.Data;


/**
 * <p>
 * 会员签到表
 * </p>
 *
 * @author Feri(邢朋辉)
 * @since 2020-06-08
 */
@Data
public class UserMemeberSign  {

    private Long id;

    private Long mid;

    /**
     * 签到时间
     */
    private Date ctime;

    /**
     * 连续的天数
     */
    private Integer days;

    /**
     * 基础奖励
     */
    private Integer basescore;

    /**
     * 额外奖励
     */
    private Integer extrascore;
}
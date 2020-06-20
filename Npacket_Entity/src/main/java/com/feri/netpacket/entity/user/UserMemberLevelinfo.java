package com.feri.netpacket.entity.user;

import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>
 * 会员等级表
 * </p>
 *
 * @author Feri(邢朋辉)
 * @since 2020-06-08
 */
@Data
public class UserMemberLevelinfo {

    private Long id;

    private String name;

    /**
     * 所需成长值
     */
    private Integer growth_point;

    /**
     * 是否为默认等级：0->不是；1->是
     */
    private Integer default_status;

    /**
     * 免运费标准
     */
    private BigDecimal free_freight_point;

    /**
     * 每次评价获取的成长值
     */
    private Integer comment_growth_point;

    /**
     * 是否有免邮特权
     */
    private Integer priviledge_free_freight;

    /**
     * 是否有签到特权
     */
    private Integer priviledge_sign_in;

    /**
     * 是否有评论获奖励特权
     */
    private Integer priviledge_comment;

    /**
     * 是否有专享活动特权
     */
    private Integer priviledge_promotion;

    /**
     * 是否有会员价格特权
     */
    private Integer priviledge_member_price;

    /**
     * 是否有生日特权
     */
    private Integer priviledge_birthday;

    private String note;
}

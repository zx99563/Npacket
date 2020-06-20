package com.feri.netpacket.entity.user;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 积分消费设置
 * </p>
 *
 * @author Feri(邢朋辉)
 * @since 2020-06-08
 */
@Data
public class UserIntegrationConsume  {

    private Long id;

    /**
     * 每一元需要抵扣的积分数量
     */
    private Integer deduction_per_amount;

    /**
     * 每笔订单最高抵用百分比
     */
    private Integer max_percent_per_order;

    /**
     * 每次使用积分最小单位100
     */
    private Integer use_unit;

    /**
     * 是否可以和优惠券同用；0->不可以；1->可以
     */
    private Integer coupon_status;


}

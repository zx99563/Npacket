package com.feri.netpacket.provider.model;

import lombok.Data;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-18 11:51
 */
@Data
public class AliPayRefound {
    private String out_trade_no; //订单号
    private double refund_amount;//退款的金额
    private String refund_reason;//退款的原因
}

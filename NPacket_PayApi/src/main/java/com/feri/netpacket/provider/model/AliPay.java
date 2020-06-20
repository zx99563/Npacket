package com.feri.netpacket.provider.model;

import lombok.Data;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-18 11:34
 */
@Data
public class AliPay {
    private String out_trade_no;// 订单号
    private double total_amount;// 订单总金额 单位 元
    private String subject;// 订单中商品的描述信息
}
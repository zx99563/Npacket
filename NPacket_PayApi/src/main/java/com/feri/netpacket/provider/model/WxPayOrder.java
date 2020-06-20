package com.feri.netpacket.provider.model;

import lombok.Data;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-18 17:56
 */
@Data
public class WxPayOrder {
    private String body;
    private String out_trade_no;
    private int total_fee; //单位为分
}

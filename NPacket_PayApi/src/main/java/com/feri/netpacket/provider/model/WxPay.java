package com.feri.netpacket.provider.model;

import lombok.Data;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-18 17:53
 */
@Data
public class WxPay {
    private String appid;
    private String mch_id;
    private String nonce_str;
    private String spbill_create_ip;
    private String notify_url;
    private String trade_type="NATIVE";
}

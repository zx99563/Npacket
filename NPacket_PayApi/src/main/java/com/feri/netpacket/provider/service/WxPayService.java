package com.feri.netpacket.provider.service;

import com.feri.common.vo.R;
import com.feri.netpacket.provider.model.WxPayOrder;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-18 18:13
 */
public interface WxPayService {
    //发起支付
    R sendPay(WxPayOrder payOrder);
    //查询支付
    R checkPay(String oid);
    //关闭支付
    R closePay(String oid);
}

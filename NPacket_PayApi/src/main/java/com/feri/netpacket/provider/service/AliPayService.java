package com.feri.netpacket.provider.service;

import com.feri.common.vo.R;
import com.feri.netpacket.provider.model.AliPay;
import com.feri.netpacket.provider.model.AliPayRefound;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-18 12:20
 */
public interface AliPayService {
    //发起支付
    R sendPay(AliPay pay);
    //查询支付
    R checkPay(String oid);
    //关闭支付
    R closePay(String oid);
    //退款
    R refoundPay(AliPayRefound refound);

}

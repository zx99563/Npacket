package com.feri.netpacket.provider.service.impl;

import com.feri.common.vo.R;
import com.feri.netpacket.provider.core.wxpay.WxPayUtil;
import com.feri.netpacket.provider.model.WxPayOrder;
import com.feri.netpacket.provider.service.WxPayService;
import org.springframework.stereotype.Service;

import java.util.Base64;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-18 18:14
 */
@Service
public class WxPayServiceImpl implements WxPayService {
    private String baseURL="http://localhost:8098/api/qrcode/payqrcode.do?msg=";
    @Override
    public R sendPay(WxPayOrder payOrder) {
        String qrcode= WxPayUtil.sendPay(payOrder);
        if(qrcode!=null && qrcode.length()>0){
            String q= Base64.getUrlEncoder().encodeToString(qrcode.getBytes());
            return R.setOK(baseURL+q);
        }
        return R.setERROR();
    }

    @Override
    public R checkPay(String oid) {
        if(WxPayUtil.checkPay(oid)){
            return R.setOK();
        }else {
            return R.setERROR();
        }
    }

    @Override
    public R closePay(String oid) {
        if(!WxPayUtil.checkPay(oid)) {
            if (WxPayUtil.closePay(oid)) {
                return R.setOK();
            } else {
                return R.setERROR();
            }
        }else {
            return R.setERROR();
        }
    }
}

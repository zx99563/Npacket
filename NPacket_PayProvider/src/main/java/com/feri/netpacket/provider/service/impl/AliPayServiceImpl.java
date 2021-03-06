package com.feri.netpacket.provider.service.impl;

import com.feri.common.vo.R;
import com.feri.netpacket.provider.core.alipay.AliPayUtil;
import com.feri.netpacket.provider.model.AliPay;
import com.feri.netpacket.provider.service.AliPayService;
import org.springframework.stereotype.Service;

import java.util.Base64;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-18 12:22
 */
@Service
public class AliPayServiceImpl implements AliPayService {
    private String baseURL="http://localhost:8080/provider/qrcode/payqrcode.do?msg=";
    @Override
    public R sendPay(AliPay pay) {
        String qrcode=AliPayUtil.createQrcodeUrl(pay);
        if(qrcode!=null && qrcode.length()>0){
            return R.setOK(baseURL+ Base64.getUrlEncoder().encodeToString(qrcode.getBytes()));
        }
        return R.setERROR();
    }

    @Override
    public R checkPay(String oid) {
        if(AliPayUtil.checkPayStatus(oid)){
            return R.setOK();
        }else {
            return R.setERROR();
        }
    }
}

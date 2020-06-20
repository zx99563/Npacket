package com.feri.netpacket.provider.controller;

import com.feri.common.vo.R;
import com.feri.netpacket.provider.model.AliPay;
import com.feri.netpacket.provider.service.AliPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-18 12:25
 */
@RestController
public class AliPayController {
    @Autowired
    private AliPayService service;

    @PostMapping("/provider/alipay/sendpay.do")
    public R senPay(@RequestBody AliPay pay){
        return service.sendPay(pay);
    }
    @GetMapping("/provider/alipay/checkpay.do")
    public R check(String oid){
        return service.checkPay(oid);
    }
}
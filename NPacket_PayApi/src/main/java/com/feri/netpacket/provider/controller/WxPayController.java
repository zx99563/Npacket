package com.feri.netpacket.provider.controller;

import com.feri.common.vo.R;
import com.feri.netpacket.provider.model.WxPayOrder;
import com.feri.netpacket.provider.service.WxPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-18 18:17
 */
@RestController
@CrossOrigin
public class WxPayController {
    @Autowired
    private WxPayService service;

    @PostMapping("api/wxpay/sendpay.do")
    public R sendPay(@RequestBody WxPayOrder payOrder){
        return service.sendPay(payOrder);
    }
    @GetMapping("api/wxpay/checkpay.do")
    public R checkPay(String oid){
        return service.checkPay(oid);
    }
    @GetMapping("api/wxpay/closepay.do")
    public R closePay(String oid){
        return service.closePay(oid);
    }
}

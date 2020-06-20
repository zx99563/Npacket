package com.feri.netpacket.provider.controller;

import com.feri.netpacket.provider.service.QrCodeService;
import com.feri.netpacket.provider.util.QrCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-18 12:12
 */
@Controller
public class QrCodeController {
    @Autowired
    private QrCodeService service;

    @GetMapping("api/qrcode/showqrcode.do")
    public void showQrcode(String msg, HttpServletResponse response) throws IOException {
        service.createQrCode(msg, response);
    }
    @GetMapping("api/qrcode/payqrcode.do")
    public void payQrCode(String msg, HttpServletResponse response) throws IOException {
        service.createPayQr(msg, response);
    }
}

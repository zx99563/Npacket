package com.feri.netpacket.provider.service.impl;

import com.feri.netpacket.provider.service.QrCodeService;
import com.feri.netpacket.provider.util.QrCodeUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-18 12:15
 */
@Service
public class QrCodeServiceImpl implements QrCodeService {
    @Override
    public void createQrCode(String msg, HttpServletResponse response) {
        try {
            QrCodeUtil.createQrCode(msg,400,response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createPayQr(String msg, HttpServletResponse response) {
        //msg 为密文 base64URL的编码格式
        String c= new String(Base64.getUrlDecoder().decode(msg));
        try {
            QrCodeUtil.createQrCode(c,400,response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

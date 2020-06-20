package com.feri.netpacket.provider.service;

import javax.servlet.http.HttpServletResponse;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-18 12:13
 */
public interface QrCodeService {
    //普通内容
    void createQrCode(String msg, HttpServletResponse response);
    //内容加密
    void createPayQr(String msg, HttpServletResponse response);
}

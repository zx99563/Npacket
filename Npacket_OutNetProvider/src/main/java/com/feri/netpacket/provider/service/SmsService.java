package com.feri.netpacket.provider.service;

import com.feri.common.vo.R;
import com.feri.common.vo.SmsCode;

public interface SmsService {
    //发送
    R sendCode(String phone);
    //校验
    R checkCode(SmsCode code);

}

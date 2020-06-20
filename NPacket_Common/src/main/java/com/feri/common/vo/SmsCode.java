package com.feri.common.vo;

import lombok.Data;

@Data
public class SmsCode {
    private String phone;
    private int code;
}
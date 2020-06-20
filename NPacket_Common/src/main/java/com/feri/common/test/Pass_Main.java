package com.feri.common.test;

import com.feri.common.util.EncryptUtil;

public class Pass_Main {
    public static void main(String[] args) {
        //创建AES密钥
        String key= EncryptUtil.createAESKey();
        System.out.println(key);
        String pass="123456";
        String mw=EncryptUtil.aesenc(key,pass);
        System.out.println("密文："+mw);
        System.out.println("解密："+EncryptUtil.aesdec(key,mw));
    }
}

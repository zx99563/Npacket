package com.feri.netpacket.provider.test;

import com.feri.common.util.EncryptUtil;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-17 15:09
 */
public class Aes_Main {
    public static void main(String[] args) {
        System.out.println(EncryptUtil.createAESKey());
        System.out.println(EncryptUtil.createAESKey());
    }
}

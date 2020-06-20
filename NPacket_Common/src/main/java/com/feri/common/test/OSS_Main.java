package com.feri.common.test;

import com.feri.common.oss.OssUtil;
import com.google.gson.annotations.Since;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-09 10:59
 */
public class OSS_Main {
    public static void main(String[] args) throws IOException {
        String b="netpacket-res-911";
       // System.out.println(OssUtil.createBluck(b));
//        FileInputStream fis=new FileInputStream("H:\\图片\\b2.jpg");
//        byte[] arr=fis.readAllBytes();
//        System.out.println(OssUtil.putData(b,"b2.jpg",arr));
        System.out.println(OssUtil.checkKey(b,"b2.jpg"));
    }
}

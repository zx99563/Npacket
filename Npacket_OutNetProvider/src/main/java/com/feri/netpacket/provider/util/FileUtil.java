package com.feri.netpacket.provider.util;

import com.feri.common.oss.OssUtil;

import java.util.Random;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-09 11:38
 */
public class FileUtil {
    //重命名
    public static String rename(String filename){
        if(filename.length()>20){
            filename=filename.substring(filename.lastIndexOf(-20));
        }
        filename=System.currentTimeMillis()+"_"+new Random().nextInt(1000000)+"_"+filename;
        return filename;
    }
    //重命名 直到OSS不存在
    public static String renameOSS(String bname,String filename){

        if(filename.length()>20){
            filename=filename.substring(filename.lastIndexOf(-20));
        }

        filename=System.currentTimeMillis()+"_"+new Random().nextInt(1000000)+"_"+filename;
        while (OssUtil.checkKey(bname,filename)){
            filename=System.currentTimeMillis()+"_"+new Random().nextInt(1000000)+"_"+filename;
        }
        return filename;
    }
}

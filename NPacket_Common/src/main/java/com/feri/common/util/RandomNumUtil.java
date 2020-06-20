package com.feri.common.util;

import java.util.Random;

//随机数
public class RandomNumUtil {

    /**
     * @param len 需要的位数
     * 4 1000-9999 10^3 ~ 10^4-1
     * 随机生成 0-8999
     */
    public static int randomNum(int len){
        Random random=new Random();
        return (int)Math.pow(10,len-1)+(random.nextInt(
                (int)(Math.pow(10,len)-Math.pow(10,len-1))));
    }
}

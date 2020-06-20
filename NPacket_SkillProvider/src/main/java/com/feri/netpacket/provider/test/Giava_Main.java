package com.feri.netpacket.provider.test;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-17 14:06
 */
public class Giava_Main {
    public static void main(String[] args) {
        //1、创建限流对象 并设置每秒的生成的令牌数量  上限  令牌桶
        RateLimiter rateLimiter=RateLimiter.create(10);

        for (int i=1;i<=30;i++){
            rateLimiter.acquire();//每次请求都需要调用，如果说请求频率超过，就阻塞
            System.out.println("请求："+System.currentTimeMillis());
        }
        //90809 93707
    }
}

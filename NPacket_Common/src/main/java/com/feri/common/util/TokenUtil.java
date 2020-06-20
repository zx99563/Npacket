package com.feri.common.util;

import com.feri.common.redis.JedisUtil;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-08 14:40
 */
public class TokenUtil {
    /**
     *
     * 获取用户对象*/
    public static String getUserJSON(JedisUtil jedisUtil,String key){
        if(jedisUtil.exists(key)){
            return jedisUtil.get(key);
        }else {
            return null;
        }
    }
}

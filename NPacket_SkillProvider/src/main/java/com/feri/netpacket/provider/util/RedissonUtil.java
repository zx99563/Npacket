package com.feri.netpacket.provider.util;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-16 09:56
 */
public class RedissonUtil {
    //
    private RedissonClient redisson;

    public RedissonUtil(String host,int port,String psw){
        Config config=new Config();
        config.useSingleServer().setAddress("redis://"+host+":"+port).setPassword(psw);
        redisson=Redisson.create(config);
    }
    //操作Hash集合
    public void hadd(String key,String f,Object v){
        redisson.getMap(key).put(f,v);
    }
    public boolean checkHKey(String key,String f){
       return redisson.getMap(key).containsKey(f);
    }
    public boolean checkKey(String key){
        return redisson.getKeys().countExists(key)>0;
    }

    public String get(String key){
        return (String) redisson.getBucket(key).get();
    }
    public boolean checkSet(String key,int id){
        return redisson.getSet(key).contains(id);
    }
    public String getHashV(String key,String f){
        return (String) redisson.getMap(key).get(f);
    }
    public int getHashVInt(String key,String f){
        return (Integer) redisson.getMap(key).get(f);
    }

    //操作
    //加锁
    public void lock(String key){
        redisson.getLock(key).lock();
    }
    //释放锁
    public void unlock(String key){
        redisson.getLock(key).unlock();
    }

}

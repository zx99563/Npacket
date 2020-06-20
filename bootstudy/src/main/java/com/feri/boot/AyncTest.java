package com.feri.boot;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-04 20:12
 */
@Component
public class AyncTest {

    @Async
    public void t1(){
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"--->"+System.currentTimeMillis());
    }
    @Async
    public Future<Integer> num1(){
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+"--子线程->"+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>(1001);
    }
}

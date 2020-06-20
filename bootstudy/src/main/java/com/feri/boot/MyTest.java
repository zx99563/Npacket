package com.feri.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-04 19:58
 */
@RestController
public class MyTest {
    @GetMapping("t1.do")
    public String ok(){
        return "OK";
    }

    @Autowired
    private AyncTest t;
    @GetMapping("t2.do")
    public String t2(){
        t.t1();
        return "123"+System.currentTimeMillis();
    }
    @GetMapping("t3.do")
    public String t3() throws ExecutionException, InterruptedException {

        return "接口--->"+t.num1().get()+"---->"+System.currentTimeMillis();
    }

}

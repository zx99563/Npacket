package com.feri.netpacket.provider.controller;

import com.feri.common.vo.R;
import com.feri.netpacket.provider.service.ROSSDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-09 11:58
 */
@RestController
public class OssLogController {
    @Autowired
    private ROSSDataService service;
    //查询全部
    @GetMapping("osslogprovider/all.do")
    public R all(){
        return service.all();
    }
    @GetMapping("osslogprovider/single.do")
    public R signle(@RequestParam String key){
        return service.single(key);
    }
}

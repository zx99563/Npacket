package com.feri.netpacket.api.controller;

import com.feri.common.vo.R;
import com.feri.netpacket.api.service.OssDataService;
import com.feri.netpacket.api.service.OssService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-09 12:01
 */
@Api
@RestController
public class OssDataController {
    @Autowired
    private OssDataService service;
    //查询全部
    @GetMapping("api/ossdata/all.do")
    public R all(){
        return service.all();
    }
    @GetMapping("api/ossdata/single.do")
    public R signle(@RequestParam String key){
        return service.signle(key);
    }
}

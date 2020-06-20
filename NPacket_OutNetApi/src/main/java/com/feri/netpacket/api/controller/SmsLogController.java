package com.feri.netpacket.api.controller;

import com.feri.common.vo.R;
import com.feri.netpacket.api.service.SmsLogService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-05 14:44
 */
@Api
@RestController
public class SmsLogController {
    @Autowired
    private SmsLogService service;
    //查询
    @GetMapping("/api/sms/all.do")
    public R all(){
        return R.setOK(service.all());
    }
}

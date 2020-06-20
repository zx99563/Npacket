package com.feri.netpacket.provider.controller;

import com.feri.common.vo.R;
import com.feri.netpacket.entity.user.UserMemberDetail;
import com.feri.netpacket.provider.service.UserMemberDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-08 14:29
 */
@RestController
public class UMDetailController {
    @Autowired
    private UserMemberDetailService service;

    //查询
    @GetMapping("/userprovider/querydetail.do")
    public R detail(@RequestParam String token){
        return service.getDetail(token);
    }
    @PutMapping("/userprovider/changedetail.do")
    public R change(@RequestBody UserMemberDetail detail){
        return service.changeDetail(detail);
    }

}

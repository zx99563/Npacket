package com.feri.netpacket.provider.controller;

import com.feri.common.dto.PageDto;
import com.feri.common.vo.R;
import com.feri.netpacket.provider.service.UMSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-08 15:21
 */
@RestController
public class UMSignController {
    @Autowired
    private UMSignService signService;

    //每日签到
    @PostMapping("usersignprovider/sign.do")
    public R sign(@RequestParam String token){
        return signService.sign(token);
    }

    //查询本月签到记录
    @GetMapping("usersignprovider/signmonth.do")
    public R signMonth(String token){
        return signService.queryMoth(token);
    }
    //查询用户的签到记录
    @GetMapping("usersignprovider/signlist.do")
    public R signList(@RequestBody PageDto pageDto){
        return signService.queryPage(pageDto);
    }
}

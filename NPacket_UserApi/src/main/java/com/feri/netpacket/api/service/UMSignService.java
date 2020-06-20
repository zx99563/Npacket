package com.feri.netpacket.api.service;

import com.feri.common.dto.PageDto;
import com.feri.common.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-08 15:31
 */
@FeignClient("UserProvider")
public interface UMSignService {
    @PostMapping("usersignprovider/sign.do")
    R sign(@RequestParam String token);

    //查询本月签到记录
    @GetMapping("usersignprovider/signmonth.do")
    R signMonth(String token);
    //查询用户的签到记录
    @GetMapping("usersignprovider/signlist.do")
    R signList(@RequestBody PageDto pageDto);
}
package com.feri.netpacket.api.service;

import com.feri.common.vo.R;
import com.feri.netpacket.entity.user.UserMemberDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-08 14:33
 */
@FeignClient("UserProvider")
public interface UMDetailService {
    //查询
    @GetMapping("/userprovider/querydetail.do")
    R detail(@RequestParam String token);
    @PutMapping("/userprovider/changedetail.do")
    R change(@RequestBody UserMemberDetail detail);
}

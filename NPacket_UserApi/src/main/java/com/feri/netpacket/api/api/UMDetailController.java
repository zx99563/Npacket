package com.feri.netpacket.api.api;

import com.feri.common.vo.R;
import com.feri.netpacket.api.service.UMDetailService;
import com.feri.netpacket.entity.user.UserMemberDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-08 14:34
 */
@RestController
public class UMDetailController {
    @Autowired
    private UMDetailService service;

    //查询
    @GetMapping("/user/api/querydetail.do")
    public R detail(@RequestParam String token){
        return service.detail(token);
    }
    @PutMapping("/user/api/changedetail.do")
    public R change(@RequestBody UserMemberDetail detail){
        return service.change(detail);
    }
}

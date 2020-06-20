package com.feri.netpacket.api.service;

import com.feri.common.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-05 14:42
 */
@FeignClient("OutNetProvider")
public interface SmsLogService {
    //查询
    @GetMapping("/smsprovider/all.do")
    R all();

}

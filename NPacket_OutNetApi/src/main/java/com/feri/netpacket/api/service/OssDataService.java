package com.feri.netpacket.api.service;

import com.feri.common.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-09 11:59
 */
@FeignClient("OutNetProvider")
public interface OssDataService {
    //查询全部
    @GetMapping("osslogprovider/all.do")
    R all();
    @GetMapping("osslogprovider/single.do")
    R signle(@RequestParam String key);
}

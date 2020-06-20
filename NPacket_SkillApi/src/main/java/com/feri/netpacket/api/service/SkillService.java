package com.feri.netpacket.api.service;

import com.feri.common.dto.SkillGoodsDto;
import com.feri.common.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-17 16:19
 */
@FeignClient("SkillProvider")
public interface SkillService {
    //1.获取秒杀接口
    @PostMapping("/provider/skill/skillorder.do")
    R getSkillUrl(@RequestBody SkillGoodsDto dto);
    //2.秒杀下单接口
    @PostMapping("/provider/skill/{path}/createorder.do")
    R getSkillUrl(@PathVariable String path);
}

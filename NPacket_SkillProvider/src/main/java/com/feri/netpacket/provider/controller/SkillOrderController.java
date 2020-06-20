package com.feri.netpacket.provider.controller;

import com.feri.common.dto.SkillGoodsDto;
import com.feri.common.vo.R;
import com.feri.netpacket.provider.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-17 14:51
 */
@RestController
public class SkillOrderController {
    @Autowired
    private SkillService skillService;

    //1.获取秒杀接口
    @PostMapping("/provider/skill/skillorder.do")
    public R getSkillUrl(@RequestBody SkillGoodsDto dto){
        return skillService.createSkillUrl(dto);
    }
    //2.秒杀下单接口
    @PostMapping("/provider/skill/{path}/createorder.do")
    public R getSkillUrl(@PathVariable String path){
        return skillService.skillV2(path);
    }

}

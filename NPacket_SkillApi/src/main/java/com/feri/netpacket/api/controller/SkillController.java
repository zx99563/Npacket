package com.feri.netpacket.api.controller;

import com.feri.common.dto.SkillGoodsDto;
import com.feri.common.vo.R;
import com.feri.netpacket.api.service.SkillService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-17 16:20
 */
@RestController
@CrossOrigin //跨域
public class SkillController {
    @Autowired
    private SkillService skillService;

    //1.获取秒杀接口
    @HystrixCommand(fallbackMethod = "skillurlFB")
    @PostMapping("/api/skill/skillorder.do")
    public R getSkillUrl(@RequestBody SkillGoodsDto dto){
        return skillService.getSkillUrl(dto);
    }
    //降级方法 对应的接口出故障的时候就会执行降级
    public R skillurlFB(SkillGoodsDto dto)
    {
       return R.setERROR("服务不可用");
    }

    //2.秒杀下单接口
    @HystrixCommand(fallbackMethod = "skillFB")
    @PostMapping("/api/skill/{path}/createorder.do")
    public R getSkillUrl(@PathVariable String path){
        return skillService.getSkillUrl(path);
    }
    //
    public R skillFB(String path){
        return R.setERROR("服务不可用");
    }
}

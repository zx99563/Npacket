package com.feri.netpacket.provider.service;

import com.feri.common.dto.SkillGoodsDto;
import com.feri.common.vo.R;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-16 09:32
 */
public interface SkillService {

    //秒杀接口地址
    R createSkillUrl(SkillGoodsDto dto);
    //秒杀下单
    R skill(String token,int gid);

    //秒杀下单
    R skillV2(String path);

}

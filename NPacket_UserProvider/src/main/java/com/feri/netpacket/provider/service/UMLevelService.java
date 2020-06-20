package com.feri.netpacket.provider.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feri.common.vo.R;
import com.feri.netpacket.entity.user.UserMemberLevel;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-08 14:37
 */
public interface UMLevelService{
    //查询
    R getLevel(String token);
    //新增成长值
    R addGrow(String token);
}

package com.feri.netpacket.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feri.netpacket.entity.skill.SkillOrder;
import org.apache.ibatis.annotations.Update;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-16 18:23
 */
public interface SkillOrderDao extends BaseMapper<SkillOrder> {
    @Update(("update t_order set status=#{flag} where id=#{oid}"))
    int changeStatus(int flag);
}

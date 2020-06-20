package com.feri.netpacket.provider.dao;

import com.feri.netpacket.entity.skill.SkillGoods;
import org.apache.ibatis.annotations.Select;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-16 10:20
 */
public interface SkillGoodsDao {
    @Select("select * from t_seckill where gid=#{gid}")
    SkillGoods getById(int gid);
}

package com.feri.netpacket.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feri.common.dto.UMLevelDto;
import com.feri.netpacket.entity.user.UserMemberLevel;
import org.apache.ibatis.annotations.Select;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-08 14:37
 */
public interface UMLevelDao extends BaseMapper<UserMemberLevel> {

    @Select("select ul.*,umli.name lmsg from user_member_level ul left join user_member_levelinfo umli on ul.level=umli.id where ul.mid=#{mid}")
    UMLevelDto getByMid(int mid);
}

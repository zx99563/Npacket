package com.feri.netpacket.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feri.netpacket.entity.resource.ResOSSData;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-09 11:29
 */
public interface ROSSDataDao extends BaseMapper<ResOSSData> {
    @Delete("delete from res_oss_data where bucket=#{b},objkey=#{o}")
    int delKey(@Param("b") String b, @Param("o") String o);
}

package com.feri.netpacket.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feri.netpacket.entity.user.UserMemberSocre;
import com.feri.netpacket.entity.user.UserMemeberSign;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 会员积分表
 * </p>
 *
 * @author Feri(邢朋辉)
 * @since 2020-06-08
 */
public interface UMSignDao extends BaseMapper<UserMemeberSign> {

    //直接在sql中运算
    @Select("select * from user_memeber_sign where mid=#{mid} and date_format(ctime,'%Y-%m-%d')=#{cdate}")
    UserMemeberSign getToDay(int mid,String cdate);

    @Select("select * from user_memeber_sign where mid=#{mid} order by ctime desc limit 1")
    UserMemeberSign getLastSign(int mid);

    //查询用户的本月签到记录
    @Select("select * from user_memeber_sign where mid=#{mid} and date_format(ctime,'%Y-%m')=date_format(now(),'%Y-%m')")
    List<UserMemeberSign> getByMid(int mid);
}

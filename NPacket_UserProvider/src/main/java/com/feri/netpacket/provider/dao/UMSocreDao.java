package com.feri.netpacket.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feri.netpacket.entity.user.UserMemberSocre;
import lombok.Data;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 会员积分表
 * </p>
 *
 * @author Feri(邢朋辉)
 * @since 2020-06-08
 */
public interface UMSocreDao  extends BaseMapper<UserMemberSocre> {
    @Update("update user_member_socre set integration=integration+#{socre},history_integration=history_integration+#{score} where mid=#{mid}")
    int changeScore(@Param("mid") int mid,@Param("score") int socre);
}

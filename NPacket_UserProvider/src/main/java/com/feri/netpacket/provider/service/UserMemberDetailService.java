package com.feri.netpacket.provider.service;

import com.feri.common.vo.R;
import com.feri.netpacket.entity.user.UserMemberDetail;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-08 14:19
 */
public interface UserMemberDetailService {
    //查询详情
    R getDetail(String token);
    //完善
    R changeDetail(UserMemberDetail detail);

}

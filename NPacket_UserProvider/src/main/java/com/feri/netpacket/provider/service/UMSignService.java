package com.feri.netpacket.provider.service;

import com.feri.common.dto.PageDto;
import com.feri.common.vo.R;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-08 14:50
 */
public interface UMSignService {
    //签到
    R sign(String token);
    //查询这个月的签到数据
    R queryMoth(String token);
    //查询签到记录
    R queryPage(PageDto pageDto);

}

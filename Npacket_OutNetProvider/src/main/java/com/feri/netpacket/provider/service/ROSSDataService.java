package com.feri.netpacket.provider.service;

import com.feri.common.vo.R;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-09 11:30
 */
public interface ROSSDataService {
    //查询
    R single(String key);
    //查询全部
    R all();
}

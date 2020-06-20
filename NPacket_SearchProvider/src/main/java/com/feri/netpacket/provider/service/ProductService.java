package com.feri.netpacket.provider.service;

import com.feri.common.vo.R;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-11 14:15
 */
public interface ProductService {
    //搜索 -传递条件
    R search(String msg);

    //批量操作 同步数据 新增、修改、删除
    R syncData();
}

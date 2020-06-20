package com.feri.netpacket.provider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feri.common.vo.R;
import com.feri.netpacket.entity.resource.ResOSSData;
import com.feri.netpacket.provider.dao.ROSSDataDao;
import com.feri.netpacket.provider.service.ROSSDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-09 11:55
 */
@Service
public class ROSSDataServiceImpl implements ROSSDataService {
    @Autowired
    private ROSSDataDao dataDao;
    @Override
    public R single(String key) {
        return R.setOK(dataDao.selectOne(new QueryWrapper<ResOSSData>().eq("objkey",key)));
    }

    @Override
    public R all() {
        return R.setOK(dataDao.selectList(null));
    }
}

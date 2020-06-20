package com.feri.netpacket.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.feri.common.redis.JedisUtil;
import com.feri.common.redis.RedisKey;
import com.feri.common.util.TokenUtil;
import com.feri.common.vo.R;
import com.feri.netpacket.entity.user.UserMember;
import com.feri.netpacket.provider.dao.UMLevelDao;
import com.feri.netpacket.provider.service.UMLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-08 14:37
 */
@Service
public class UMLevelServiceImpl implements UMLevelService {
    @Autowired
    private UMLevelDao levelDao;
    @Autowired
    private JedisUtil jedisUtil;
    @Override
    public R getLevel(String token) {
        UserMember member= JSON.parseObject(TokenUtil.getUserJSON(jedisUtil, RedisKey.LOGIN_TOKEN+token),
                UserMember.class);
        if(member!=null){
            return R.setOK(levelDao.getByMid(member.getId()));
        }
        return R.setERROR();
    }

    @Override
    public R addGrow(String token) {
        //增加成长值  结合订单
        return null;
    }
}

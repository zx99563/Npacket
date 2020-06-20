package com.feri.netpacket.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feri.common.dto.PageDto;
import com.feri.common.redis.JedisUtil;
import com.feri.common.redis.RedisKey;
import com.feri.common.type.IntegrationSourceType;
import com.feri.common.type.IntegrationType;
import com.feri.common.util.DateUtil;
import com.feri.common.util.TokenUtil;
import com.feri.common.vo.R;
import com.feri.netpacket.entity.user.UserIntegrationHistory;
import com.feri.netpacket.entity.user.UserMember;
import com.feri.netpacket.entity.user.UserMemeberSign;
import com.feri.netpacket.provider.dao.UIHistoryDao;
import com.feri.netpacket.provider.dao.UMSignDao;
import com.feri.netpacket.provider.dao.UMSocreDao;
import com.feri.netpacket.provider.service.UMSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-08 14:51
 */
@Service
public class UMSignServiceImpl implements UMSignService {

    @Autowired
    protected JedisUtil jedisUtil;

    @Autowired
    private UMSignDao signDao;
    @Autowired
    private UMSocreDao socreDao;
    @Autowired
    private UIHistoryDao historyDao;
    @Override
    public R sign(String token) {
        //每日签到
        UserMember member= JSON.parseObject(TokenUtil.getUserJSON(jedisUtil, RedisKey.LOGIN_TOKEN+token),
                UserMember.class);
        Random random=new Random();
        //1.验证是否可以签到
       // UserMemeberSign sign=signDao.getToDay(member.getId(), DateUtil.getTime());
        //最后一次签到数据
        UserMemeberSign sign=signDao.getLastSign(member.getId());
        //本次签到的积分
        int s=random.nextInt(8)+1;
        int exr=0;//记录奖励的积分
        String msg;
        int days;
        if(sign==null){
            //这是第一次签到 首签奖励500积分
            exr=500;
            msg="首次签到，额外奖励500积分";
            days=1;
            return R.setOK();
        }else {

            //今日是否可以签到
            if(DateUtil.getTime().equals(new SimpleDateFormat("yyyy-MM-dd").format(sign.getCtime()))){
                return R.setERROR("亲，今日已签到，请明天再来！");
            }else {
                //验证 连续签到还是 断签到
                if(DateUtil.checkDate(sign.getCtime())){
                    //昨天签到了
                    // 3 7 30 365
                    if((sign.getDays()+1)%365==0){
                        //连续签到1年
                        exr=1000;
                        msg="恭喜你，连续签到1年，奖励1000积分";
                    }else if((sign.getDays()+1)%30==0){
                        exr=100;
                        msg="恭喜你，连续签到1个月，奖励100积分";
                    }else if((sign.getDays()+1)%7==0){
                        exr=10;
                        msg="恭喜你，连续签到7天，奖励10积分";
                    }else if((sign.getDays()+1)%3==0){
                        exr=s;
                        msg="恭喜你，连续签到3天，奖励"+exr+"积分";
                    }
                    days=sign.getDays()+1;
                }else {
                    days=1;
                }
            }
            UserMemeberSign memeberSign=new UserMemeberSign();
            memeberSign.setBasescore(s);
            memeberSign.setExtrascore(exr);
            memeberSign.setMid((long)member.getId());
            memeberSign.setDays(days);
            memeberSign.setCtime(new Date());
            signDao.insert(memeberSign);
            //更新积分 用户积分表 积分变动表
            socreDao.changeScore(member.getId(),s+exr);
            // 新增记录 积分变化历史记录表
            UserIntegrationHistory history=new UserIntegrationHistory();
            history.setChange_count(s+exr);
            history.setChange_type(IntegrationType.add.getVal());
            history.setCtime(new Date());
            history.setMid((long)member.getId());
            history.setOperate_man("系统自动");
            history.setSource_type(IntegrationSourceType.Sign.getVal());
            history.setOperate_note("用户签到，奖励积分："+history.getChange_count());
            historyDao.insert(history);
            return R.setOK("签到成功，基础奖励："+s+",额外奖励："+exr);
        }
    }

    @Override
    public R queryMoth(String token) {
        UserMember member= JSON.parseObject(TokenUtil.getUserJSON(jedisUtil, RedisKey.LOGIN_TOKEN+token),
                UserMember.class);
        return R.setOK(signDao.getByMid(member.getId()));
    }

    @Override
    public R queryPage(PageDto pageDto) {
        UserMember member= JSON.parseObject(TokenUtil.getUserJSON(jedisUtil, RedisKey.LOGIN_TOKEN+pageDto.getData().toString()),
                UserMember.class);
        return R.setOK(signDao.selectPage(
                new Page<UserMemeberSign>((pageDto.getPage()-1)*pageDto.getSize(),pageDto.getSize()),
                new QueryWrapper<UserMemeberSign>().eq("mid",member.getId())));
    }
}

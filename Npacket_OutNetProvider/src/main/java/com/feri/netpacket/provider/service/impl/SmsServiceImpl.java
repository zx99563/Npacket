package com.feri.netpacket.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.feri.common.config.SmsConfig;
import com.feri.common.redis.JedisUtil;
import com.feri.common.redis.RedisKey;
import com.feri.common.util.RandomNumUtil;
import com.feri.common.vo.R;
import com.feri.common.vo.SmsCode;
import com.feri.netpacket.provider.config.RabbitMQConfig;
import com.feri.netpacket.provider.dao.SmsLogDao;
import com.feri.netpacket.provider.service.SmsService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private SmsLogDao dao;
    @Autowired
    private JedisUtil util;
    @Autowired
    private RabbitTemplate template;
    @Override
    public R sendCode(String phone) {
        int daycount=0;
        //验证今日发送的次数是否超标
        if(util.exists(RedisKey.SMS_PL_DAY+SmsConfig.SMS_CODE+":"+phone)){
            //获取次数
            daycount=util.scard(RedisKey.SMS_PL_DAY+SmsConfig.SMS_CODE+":"+phone).intValue();
            if(daycount>12){
                return R.setERROR();
            }
        }
        //验证1小时内发送的次数是否超标
        if(util.keys(RedisKey.SMS_PL_HOUR+SmsConfig.SMS_CODE+":"+phone+"*").size()>4){
            return R.setERROR();
        }
        //校验1分钟内 是否发送过短信
        if(util.exists(RedisKey.SMS_PL_MIU+ SmsConfig.SMS_CODE+":"+phone)){
            return R.setERROR();
        }
        //生成验证码
        int code;
        //校验上次的验证码是否失效
        if(util.exists(RedisKey.SMS_CODE+phone)){
            code=Integer.parseInt(util.get(RedisKey.SMS_CODE+phone));
        }else {
            code=RandomNumUtil.randomNum(6);
        }
        //改成基于RabbitMQ发送信息-->
        SmsCode smsCode=new SmsCode();
        smsCode.setCode(code);
        smsCode.setPhone(phone);
        //发送消息
        template.convertAndSend(RabbitMQConfig.smscodeQname, JSON.toJSONString(smsCode));
        return R.setOK();
    }

    @Override
    public R checkCode(SmsCode code) {
        //1.校验验证码释放有效
        if(util.exists(RedisKey.SMS_CODE+code.getPhone())){
            //取出验证码
            int c=Integer.parseInt(util.get(RedisKey.SMS_CODE+code.getPhone()));
            //比较验证码
            if(c==code.getCode()){
                //删除验证码
                util.del(RedisKey.SMS_CODE+code.getPhone());
                //验证码有效
                return R.setOK();
            }
        }
        return R.setERROR();
    }
}

package com.feri.netpacket.provider.listener;

import com.alibaba.fastjson.JSON;
import com.feri.common.config.SmsConfig;
import com.feri.common.redis.JedisUtil;
import com.feri.common.redis.RedisKey;
import com.feri.common.util.AliSmsUtil;
import com.feri.common.util.DateUtil;
import com.feri.common.vo.R;
import com.feri.common.vo.SmsCode;
import com.feri.netpacket.entity.sms.SmsLog;
import com.feri.netpacket.provider.service.SmsLogService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-06 14:42
 */
@Component
@RabbitListener(queues = "npacket.smscode")
public class SmsCodeQueueListener {
    @Autowired
    private JedisUtil util;
    @Autowired
    private SmsLogService service;
    @RabbitHandler
    public void handler(String msg){
        System.out.println("---aaaa----"+msg);
        SmsCode smsCode= JSON.parseObject(msg,SmsCode.class);
        String phone=smsCode.getPhone();
        int code=smsCode.getCode();
        //发送短信
        if(AliSmsUtil.sendSmsCode(phone,smsCode.getCode())) {
            //记录1分钟
            util.setex(RedisKey.SMS_PL_MIU + SmsConfig.SMS_CODE + ":" + phone, 60, "1");
            //记录1小时
            util.setex(RedisKey.SMS_PL_HOUR + SmsConfig.SMS_CODE + ":" + phone + ":" + System.currentTimeMillis(), 3600, "1");
            //记录1天的发送
            if (util.exists(RedisKey.SMS_PL_DAY + SmsConfig.SMS_CODE + ":" + phone)) {
                int daycount=util.scard(RedisKey.SMS_PL_DAY + SmsConfig.SMS_CODE + ":" + phone).intValue();
                util.sadd(RedisKey.SMS_PL_DAY + SmsConfig.SMS_CODE + ":" + phone, (daycount + 1) + "");
            } else {
                util.sadd(RedisKey.SMS_PL_DAY + SmsConfig.SMS_CODE + ":" + phone, 1 + "");
                //设置有效期 今日剩余的时间
                util.expire(RedisKey.SMS_PL_DAY + SmsConfig.SMS_CODE + ":" + phone, DateUtil.getLastSeconds());
            }
            //存储验证码 -Redis
            util.setex(RedisKey.SMS_CODE + phone, RedisKey.SMS_CODE_TIME, code + "");
            //记录到数据库 发送短信记录
            SmsLog log = new SmsLog();
            log.setCtime(new Date());
            log.setPhone(phone);
            log.setMsg("为" + phone + "发送短信验证码：" + code);
            service.save(log);
        }else {
            //记录到数据库 发送短信记录
            SmsLog log = new SmsLog();
            log.setCtime(new Date());
            log.setPhone(phone);
            log.setMsg("为" + phone + "发送短信验证码：发送失败！");
            service.save(log);
        }
    }
}

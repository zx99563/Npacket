package com.feri.netpacket.provider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feri.netpacket.entity.sms.SmsLog;
import com.feri.netpacket.provider.dao.SmsLogDao;
import com.feri.netpacket.provider.service.SmsLogService;
import org.springframework.stereotype.Service;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-05 09:48
 */
@Service
public class SmsLogServiceImpl extends ServiceImpl<SmsLogDao, SmsLog> implements SmsLogService {
}

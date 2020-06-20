package com.feri.netpacket.entity.sms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sms_log")
public class SmsLog {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String phone;
    private String msg;
    private Date ctime;
}

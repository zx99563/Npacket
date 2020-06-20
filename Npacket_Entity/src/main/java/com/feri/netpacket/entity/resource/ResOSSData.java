package com.feri.netpacket.entity.resource;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-09 11:27
 */
@Data
@TableName("res_oss_data")
public class ResOSSData {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String bucket;
    private String objkey;
    private String objurl;
    private Date ctime;
    private Date endtime;
}

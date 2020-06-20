package com.feri.netpacket.entity.skill;

import lombok.Data;

import java.util.Date;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-16 09:23
 */
@Data
public class SkillOrder {
    private Integer id;
    private Integer gid;
    private double tprice;
    private Integer status;//订单状态码
    private Date ctime;
}

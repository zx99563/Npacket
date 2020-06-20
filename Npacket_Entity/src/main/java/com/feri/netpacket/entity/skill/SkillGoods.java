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
public class SkillGoods {
    private Integer id;
    private Integer gid;
    private double sprice;
    private Date btime;
    private Date etime;
    private String surl;
}
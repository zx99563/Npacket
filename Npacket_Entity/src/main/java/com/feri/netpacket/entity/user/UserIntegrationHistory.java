package com.feri.netpacket.entity.user;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 积分变化历史记录表
 * </p>
 *
 * @author Feri(邢朋辉)
 * @since 2020-06-08
 */
@Data
public class UserIntegrationHistory {

    private Long id;

    private Long mid;

    private Date ctime;

    /**
     * 改变类型：0->增加；1->减少
     */
    private Integer change_type;

    /**
     * 积分改变数量
     */
    private Integer change_count;

    /**
     * 操作人员
     */
    private String operate_man;

    /**
     * 操作备注
     */
    private String operate_note;

    /**
     * 积分来源：0->购物；1->管理员修改
     */
    private Integer source_type;
}
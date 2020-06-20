package com.feri.netpacket.entity.user;

import java.util.Date;
import lombok.Data;

/**
 * <p>
 * 成长值变化历史记录表
 * </p>
 *
 * @author Feri(邢朋辉)
 * @since 2020-06-08
 */
@Data
public class UserGrowthHistory  {

    private Long id;

    private Long mid;

    private Date ctime;

    /**
     * 改变类型：0->增加；1->减少
     */
    private Integer change_type;

    /**
     * 成长值改变数量
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
     * 成长值来源：0->购物；1元折现 1成长值 1->管理员修改
     */
    private Integer source_type;

}

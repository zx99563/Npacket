package com.feri.netpacket.entity.user;

import lombok.Data;


/**
 * <p>
 * 会员与产品分类关系表（用户喜欢的分类）
 * </p>
 *
 * @author Feri(邢朋辉)
 * @since 2020-06-08
 */
@Data
public class UserMemberProductcategory {
    private Long id;

    private Long mid;

    private Long product_category_id;
}
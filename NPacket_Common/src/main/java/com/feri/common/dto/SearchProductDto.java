package com.feri.common.dto;

import lombok.Data;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-11 14:12
 */
@Data
public class SearchProductDto {
    private Integer id;
    private String pname;
    private String tname;
    private int tid;
}

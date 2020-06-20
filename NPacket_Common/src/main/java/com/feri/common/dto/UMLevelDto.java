package com.feri.common.dto;

import lombok.Data;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-08 14:43
 */
@Data
public class UMLevelDto {
    private int id;
    private int mid;
    private int level;
    private String lmsg;
    private int growth;
}

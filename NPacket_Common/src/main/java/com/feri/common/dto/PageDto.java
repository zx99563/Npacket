package com.feri.common.dto;

import lombok.Data;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-08 17:57
 */
@Data
public class PageDto {
    private int page;
    private int size;
    private Object data;
}

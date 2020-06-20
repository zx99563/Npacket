package com.feri.common.dto;

import lombok.Data;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-05 14:56
 */
@Data
public class UserLoginDto {
    private String phone;
    private String psw;
}

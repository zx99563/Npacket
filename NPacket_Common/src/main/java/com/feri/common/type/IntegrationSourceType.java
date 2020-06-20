package com.feri.common.type;

/**
 * @program: Npacket_Parent
 * @description: 积分变动来源
 * @author: Feri(邢朋辉)
 * @create: 2020-06-08 18:07
 */
public enum IntegrationSourceType {
    Order(0),Register(1),Login(2),Sign(3),Admin(4);
    private int val;
    private IntegrationSourceType(int v){
        val=v;
    }

    public int getVal() {
        return val;
    }
}

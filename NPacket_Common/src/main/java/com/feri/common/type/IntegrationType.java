package com.feri.common.type;

/**
 * @program: Npacket_Parent
 * @description: 积分变动的类型 0新增 1减少
 * @author: Feri(邢朋辉)
 * @create: 2020-06-08 18:03
 */
public enum IntegrationType {
    add(0),decrease(1);
    private int val;
    private IntegrationType(int v){
        val=v;
    }

    public int getVal() {
        return val;
    }
}

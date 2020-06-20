package com.feri.common.type;

/**
 * @program: Npacket_Parent
 * @description: 订单的状态
 * @author: Feri(邢朋辉)
 * @create: 2020-06-16 09:29
 */
public enum OrderStatus {
    未付款(1),未发货(2),未确认(3),未评价(4),已评价(5),取消订单(6),
    超时订单(7);
    private int val;
    private OrderStatus(int v){
        val=v;
    }

    public int getVal() {
        return val;
    }
}

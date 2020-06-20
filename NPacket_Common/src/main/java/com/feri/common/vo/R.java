package com.feri.common.vo;

import lombok.Data;

@Data
public class R {
    private int code;//状态码
    private String msg;//说明内容
    private Object data;//携带的数据

    public static R setR(int code,String msg,Object data){
        R r=new R();
        r.code=code;
        r.data=data;
        r.msg=msg;
        return r;
    }
    //设置成功
    public static R setOK(){
        return setR(200,"OK",null);
    }
    public static R setOK(Object data){
        return setR(200,"OK",data);
    }
    //设置失败
    public static R setERROR(){
        return setR(400,"ERROR",null);
    }
    public static R setERROR(String msg){
        return setR(400,msg,null);
    }

}

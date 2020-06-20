package com.feri.common.exception;

/**
 * 自定义用户鉴权异常*/
public class UserAuthException extends Exception{
    public UserAuthException(){

    }
    public UserAuthException(String msg){
        super(msg);
    }
}

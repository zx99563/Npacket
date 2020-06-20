package com.feri.netpacket.webs.server;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-17 16:43
 */
@ServerEndpoint("/api/chat/{nickname}")  //发布服务端 通信地址
@Component
@Scope(scopeName = "prototype") //一定要设置为多例  一个连接就是一个实例
public class WebSocketServer {
    //记录当前在线的对象信息
    public  static ConcurrentHashMap<String,WebSocketServer> map=new ConcurrentHashMap<>();
    private Session session;
    private String nickname;
    public WebSocketServer(){
        System.out.println("服务端已经启动……");
    }
    @OnOpen
    public void open(Session session,@PathParam("nickname") String name) throws IOException {
        System.out.println(name+" 连接成功");
        if(!map.containsKey(name)){
            this.session=session;
            nickname=name;
            map.put(nickname,this);
            session.getBasicRemote().sendText("欢迎  "+name+" 的到来！");
        }else {
            //发消息
            session.getBasicRemote().sendText("亲，昵称已占用！");
        }
    }
    @OnMessage
    public void message(String msg,Session session){
        System.out.println("接收消息："+msg);
    }
    @OnClose
    public void close(Session session){
        System.out.println(nickname+" 退出了");
        map.remove(nickname);
    }
    @OnError
    public void error(Session session,Throwable throwable){
        System.out.println("出错啦--"+throwable.getMessage());
    }
}
package com.feri.common.redis;

/**
 * @program: Npacket_Parent
 * @description: 存储所有Redis的key
 * @author: Feri(邢朋辉)
 * @create: 2020-06-05 10:24
 */
public class RedisKey {
    //短信验证码
    public static final String SMS_CODE="npacket:sms:code:";//后面追加手机号
    public static final int SMS_CODE_TIME=600;//验证码有效期

    //短信频率
    //存储 1分钟内
    public static final String SMS_PL_MIU="npacket:sms:pl:miu:";//后面追加 模板和手机号
    //存储 1小时内发过的 4条 有效期1小时
    public static final String SMS_PL_HOUR="npacket:sms:pl:hou:";//后面追加 模板和手机号和随机码
    //存储1个自然日 有效期 今日剩余的时间 Set类型
    public static final String SMS_PL_DAY="npacket:sms:pl:day:";//后面追加 模板和手机号

    //登陆相关
    public static final String LOGIN_PHONE="npackte:login:phone:";//后面追加手机号+时间戳 值：存储令牌

    //记录令牌对应的手机号
    public static final String LOGIN_TOKEN="npackte:login:token:";//后面追加令牌值：用户信息

    public static final int LOGIN_TIME=1800;//有效期
    //记录被挤掉的令牌 Hash类型 字段：令牌 值记录：
    public static final String LOGIN_EDGE="npackte:login:edge";//


    //秒杀对应的各种Key
    //秒杀商品 缓存 Hash类型 字段：商品id 值：商品对应的库存 设置有效期 秒杀的结束时间
    public static final String SKILL_GOODS="npacket:skill:goodsstock";//
    //存放秒杀订单 Hash类型 字段：用户id:商品ID 值：订单对象数据
    public static final String SKILL_ORDER="npacket:skill:order";
    //记录秒杀的隐藏地址 Hash类型 字段：商品id 值：对应的秒杀地址（密文）
    public static final String SKILL_GOODSURL="npacket:skill:gurl";//

    //实现秒杀的分布式锁的名称
    public static final String SKILL_LOCKKEY="npacket:skill:lockkey";

    //记录秒杀的黑名单 set 类型 记录不能参与秒杀的用户id
    public static final String SKILL_HMD="npacket:skill:forbid";//禁止

}

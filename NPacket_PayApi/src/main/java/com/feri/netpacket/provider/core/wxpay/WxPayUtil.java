package com.feri.netpacket.provider.core.wxpay;

import ch.qos.logback.core.joran.spi.XMLUtil;
import com.feri.netpacket.provider.model.WxPay;
import com.feri.netpacket.provider.model.WxPayOrder;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-18 17:41
 */
public class WxPayUtil {
    //秘钥信息  公司提供
    private static final String APPID="wx632c8f211f8122c6";
    private static final String MCHID="1497984412";
    private static final String APPKEY="sbNCm1JnevqI36LrEaxFwcaT0hkGxFnC";
    //微信支付路径
    public static final String WP_PAY="https://api.mch.weixin.qq.com/pay/unifiedorder";
    public static final String WP_CLOSWPAY="https://api.mch.weixin.qq.com/pay/closeorder";
    public static final String WP_QUERYPAY="https://api.mch.weixin.qq.com/pay/orderquery";

    //回调接口
    public static final String WP_CALLBACKURL="http://localhost:8080/payResout.do";
    /**
     * 第一步，设所有发送或者接收到的数据为集合M，将集合M内非空参数值的参数按照参数名ASCII码从小到大排序（字典序），使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串stringA。
     *
     * 特别注意以下重要规则：
     *
     * ◆ 参数名ASCII码从小到大排序（字典序）；
     * ◆ 如果参数的值为空不参与签名；
     * ◆ 参数名区分大小写；
     * ◆ 验证调用返回或微信主动通知签名时，传送的sign参数不参与签名，将生成的签名与该sign值作校验。
     * ◆ 微信接口可能增加字段，验证签名时必须支持增加的扩展字段
     * 第二步，在stringA最后拼接上key得到stringSignTemp字符串，并对stringSignTemp进行MD5运算，再将得到的字符串所有字符转换为大写，得到sign值signValue。*/
    public static String createSign(TreeMap<String,String> params){
        //使用URL键值对的格式
        StringBuffer buffer=new StringBuffer();
        for (String p:params.keySet()) {
            buffer.append(p+"="+params.get(p)+"&");
        }
        //在stringA最后拼接上key得到stringSignTemp字符串
        buffer.append("key="+APPKEY);
        //进行MD5运算，再将得到的字符串所有字符转换为大写
        String sign=MD5Util.MD5Encode(buffer.toString(),"UTF-8").toUpperCase();
        return sign;
    }
    /**
     * 生成随机字符串 32位以内 */
    public static String createRandomStr(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static TreeMap<String,String> createPay(){
        TreeMap<String,String> params=new TreeMap<>();
        WxPay wxPay=new WxPay();
        wxPay.setAppid(APPID);
        wxPay.setMch_id(MCHID);
        wxPay.setNonce_str(createRandomStr());
        wxPay.setNotify_url(WP_CALLBACKURL);
        wxPay.setSpbill_create_ip("127.0.0.1");
        Class clz=wxPay.getClass();
        Field[] arrF=clz.getDeclaredFields();
        for(Field f:arrF){
            try {
                //设置是否过滤校验
                f.setAccessible(true);
                params.put(f.getName(),f.get(wxPay).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return params;
    }
    /**
     * 发起微信支付
     * @param payOrder 支付订单信息
     * @return 支付链接*/
    public static String sendPay(WxPayOrder payOrder){
        TreeMap<String,String> params=createPay();
        params.put("body",payOrder.getBody());
        params.put("total_fee",payOrder.getTotal_fee()+"");
        params.put("out_trade_no",payOrder.getOut_trade_no());
        //获取签名
        String sign=createSign(params);
        params.put("sign",sign);
        String requestXml=XmlUtil.createXml(params);
        String responseXml=HttpUtil.postData(WP_PAY,requestXml);
        if(responseXml!=null && responseXml.length()>0){
            Map<String,Object> map= XmlUtil.parseXml(responseXml);
            if(map.containsKey("code_url")){
                return map.get("code_url").toString();
            }
        }
        return null;
    }
    /**
     * 微信支付查询
     * @param oid 支付订单号
     * @return 支付是否成功*/
    public static boolean checkPay(String oid){
        TreeMap<String,String> params=new TreeMap<>();
        params.put("appid",APPID);
        params.put("mch_id",MCHID);
        params.put("nonce_str",createRandomStr());
        params.put("out_trade_no",oid);
        //获取签名
        String sign=createSign(params);
        params.put("sign",sign);
        String requestXml=XmlUtil.createXml(params);
        String responseXml=HttpUtil.postData(WP_QUERYPAY,requestXml);
        System.out.println(responseXml);
        if(responseXml!=null && responseXml.length()>0){

            Map<String,Object> map= XmlUtil.parseXml(responseXml);

            if(map.containsKey("trade_state")){
                return map.get("trade_state").toString().equals("SUCCESS");
            }
        }
        return false;
    }

    /**
     * 微信支付关闭
     * @param oid 支付订单号
     * @return 关闭支付是否成功*/
    public static boolean closePay(String oid){
        TreeMap<String,String> params=new TreeMap<>();
        params.put("appid",APPID);
        params.put("mch_id",MCHID);
        params.put("nonce_str",createRandomStr());
        params.put("out_trade_no",oid);
        //获取签名
        String sign=createSign(params);
        params.put("sign",sign);
        String requestXml=XmlUtil.createXml(params);
        String responseXml=HttpUtil.postData(WP_CLOSWPAY,requestXml);
        if(responseXml!=null && responseXml.length()>0){
            Map<String,Object> map= XmlUtil.parseXml(responseXml);
            if(map.containsKey("result_code")){
                return map.get("result_code").toString().equals("SUCCESS");
            }
        }
        return false;
    }

}

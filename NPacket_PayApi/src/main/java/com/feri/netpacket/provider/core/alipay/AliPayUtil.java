package com.feri.netpacket.provider.core.alipay;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.feri.netpacket.provider.model.AliPay;
import com.feri.netpacket.provider.model.AliPayRefound;

/**
 * @program: Npacket_Parent
 * @description: 封装支付宝支付
 * @author: Feri(邢朋辉)
 * @create: 2020-06-18 11:25
 */
public class AliPayUtil {

    private static final String APP_ID="2017091608770636";
    private static final String APP_PRIVATE_KEY="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCh1qI8uo1qhrcePsa5JUAoYUX8HfPuBt7kc90aCP1M/v61/uzaU/lyGQeChKV3jdDTn2Lcq6kT5JBl3TLiaYHmO6cId1nQAIUxiT9zhB9crc4wAx8CVabMbcqUefs7Xsp+YhhUgU5X6GOS3emkUeL7RegBnL8vayfEBeUDgBxsk/K/VygBA8sapsEhnoOrB6bhMY4GaJrxb0kg9Ej8x4kpExLcxkT+UgcOiJvh6vpBZo5CJsiPQkFvSsNsWY2uSDudSL/KqpMxz+yPfVvZDt4fOfyi+CfYR43Jlo4tsT7joqH2JT06BH+KdJyc1D3Lqw7w/WdmZtmoLghH0kRZawrLAgMBAAECggEAYYtpm+rhQ7zQ8HTr+DogknYW5Z/0H5qai93d/Uw/yEHFqlJt1iZZKlE1upBS311l6beesdzxeuD/u7X4bokjV27K/YpaYsl9fl74FJslAApuRXgMH68aawsd2CIxsBYxPL3JZl3Np6SVJ7eDlJwakFMRRK+CeIVAoaDf6R01hKctkYnnE0wT+ffQNKWsISoEyiKVT3g5fur7iPOuDlDXsfi6Mm+e75wCXTmRRHmb8lPBAMLV+Kj5DFxg8dwNz81Fs4ZM2Aq0lBaTfy1H1zSlM1m42wcsMYDcgdEH9aq+OgqK+cny6umgs7/Alg7IgV/9b7AhKdvAqLy2ERUJtooj2QKBgQDeIoDW3HuTq7sBaBnu63f7icT2RM3fApfOiGM4UDtxPvc5dS5S//o3E8p+rbp21FfBeyLOJFd9dg/eu+ETA+63QMPw4Kq4AH/EA5AFohaOQ0IKFDjYyxfyD8ajA4USDwdiaW2/vmMeAtGSv+W5zWb9/t49LOTwzEW904+yOGcmhQKBgQC6guDZ0Ob4o9nx5XwZXEe2di4MupARHceGzmolyDvs3Qi/w+8QntrDvfqIJoqoxOG5NVi3jtjkqtJtMaPyxqNWTabWOOTLbrsqlvPUmeCl0j3FVFKAGcV7/b9XkLvh1DtnIe6rhhZCVB4e4bL/katpOTgulhmSMaWIaztGU0F1DwKBgQCTeobdn/6vuSlsMqhdFppPN1W8R0wDjt4o8iYlwibk9e//hswdsPN307zyQ/dzY2FsBIvEHx6zHkpFD6nMDSVVJzuv1gmiJjqtccwR4V5mT0MuG+TuElCwlkbD/ddAeRfm/6Ys0oNN7oMjkiI8LKH/alI0fXT2Zji7YhWaNpZNXQKBgEU6q0duWS1VdGJrcgLf0+aQO0uSPEN+MD+Dgrb/ee7TpJm5mpUqwb0CWWoMFE/MtJRQjtujdDJ8jZrmYBqPTLWOIS1G9PXl5idK3Lq/Wzlxrmf+gpj19+2sJEfWe0a5xkrjt3mHTd/U5VFFKXHfmiZ2jLoOEPPI5c6bLudNo/BVAoGBAMvwRxLO4xb11Ip4rnEHkw3Qn8lrddoC3/m7haHYZ5DyGe8wdCdEi6wyk5MvlNQdqdVg5bqV0AiotIBcd5Pemabun2WaB11h/6SSb6wKY4Fnz+H155zaEww4no9BTG9llqQV7H8AS77dN1bxhcpE/MGFoB9JFU0D+BwXAnth4z1u";
    private static final String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAx7jJT+PSEM6ZiimTW0SGUfg4cJU04H/mQqkL2mk7KaHXFQqMh4US6xYkDlaEXzOOfxevuBqWOaB4/8TleO1CHZHXWHu9Xc+iYtJPNJGrxoGLM+6Cg9IafJTygRoaqdH0SoVMpxFdOpUftNdXHO+G0ZpS/7c1zpn8G64zN5J17IFrLcUlsEnSgOrJxsS2Q50b44er0KQlj76pehB2sTveHS2vdhqXzrv+oq99XtUKEY1a3nwDjXneI7YYKLHD9KU53pti/ibLDkOEjO4+DRowd+wfSwkmWGVL3X320mvCfrg/aMN71B/cyyhW0mQ4cxqh2UcnpxLm0v/+uC7dSCyAJwIDAQAB";

    private static AlipayClient alipayClient;

   static {
   alipayClient= new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
               APP_ID,
               APP_PRIVATE_KEY,
               "json",
               "UTF-8",
               ALIPAY_PUBLIC_KEY,
               "RSA2");
   }


    //实现预支付请求 返回支付二维码的码串
    public static String createQrcodeUrl(AliPay pay){
        //1、创建请求对象
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        //2、设置请求内容
        request.setBizContent(JSON.toJSONString(pay));
        try {
            //3、获取请求结果
            AlipayTradePrecreateResponse response=alipayClient.execute(request);
            //4、校验响应是否成功
            if(response.isSuccess()){
                //5、返回码串 支付链接
                return response.getQrCode();
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }
    //查询订单是否支付成功
    public static boolean checkPayStatus(String oid){
       //1、创建请求对象 实现查询
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        //2、设置查询的内容
        request.setBizContent("{\"out_trade_no\":\""+oid+"\"}");
        //3、获取请求结果
        try {
            AlipayTradeQueryResponse response=alipayClient.execute(request);
            //4、校验是否成功
            if(response.isSuccess()){
               return response.getTradeStatus().equals("TRADE_SUCCESS");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return false;
    }
    //取消订单(未支付)
    public static boolean closeOrder(String oid){
        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
        request.setBizContent("{\"trade_no\":\""+oid+"\"}");
        try {
            AlipayTradeCloseResponse response = alipayClient.execute(request);
            if(response.isSuccess()){
                return true;
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return false;
    }
    //退款
    public static boolean refundOrder(AliPayRefound refound){
        AlipayTradeRefundRequest  request = new AlipayTradeRefundRequest();
        request.setBizContent(JSON.toJSONString(refound));
        try {
            AlipayTradeRefundResponse response = alipayClient.execute(request);
            if(response.isSuccess()){
                return Double.parseDouble(response.getRefundFee())==refound.getRefund_amount();
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return false;
    }


}

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

   private static AlipayClient alipayClient;

   static {
       alipayClient= new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
               "",
               "",
               "json",
               "UTF-8",
               "",
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

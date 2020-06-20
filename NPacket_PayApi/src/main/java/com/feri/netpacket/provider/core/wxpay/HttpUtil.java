package com.feri.netpacket.provider.core.wxpay;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-18 17:30
 */
public class HttpUtil {

    //发起请求 post
    /**
     * 实现网络请求 post
     * @param url 请求的网址
     * @param msg 请求的参数*/
    public static String postData(String url,String msg){
        String r=null;
        //1、创建请求对象
        HttpPost post=new HttpPost(url);
        //2、设置请求信息
        HttpEntity entity=new StringEntity(msg, "UTF-8");
        post.setEntity(entity);
        //3、创建客户端对象
        CloseableHttpClient client= HttpClientBuilder.create().build();
        try {
            //4、发起请求 获取响应对象
           CloseableHttpResponse response= client.execute(post);
           //5、获取响应结果
            r= EntityUtils.toString(response.getEntity());

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return r;
    }
}

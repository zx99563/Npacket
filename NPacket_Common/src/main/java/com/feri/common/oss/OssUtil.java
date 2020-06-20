package com.feri.common.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.feri.common.util.DateUtil;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: Npacket_Parent
 * @description:  基于阿里云的对象关系存储 OSS 封装工具类
 * @author: Feri(邢朋辉)
 * @create: 2020-06-09 10:46
 */
public class OssUtil {
    private static OSS client;
    static {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4GGVWnURU9QeGew8dGj5";
        String accessKeySecret = "Gd2PfiCSpzlv304nHcfrWb66JJUq9T";
        //实例化客户端对象
        client = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }
    //创建存储空间
    public static boolean createBluck(String name){
        if(client.createBucket(name)!=null){
            return true;
        }else {
            return false;
        }
    }
    //存储字符串
    public static String putStr(String bname,String key,String msg){
        client.putObject(bname,key,new ByteArrayInputStream(msg.getBytes()));
        GeneratePresignedUrlRequest request=new GeneratePresignedUrlRequest(bname,key);
        //设置链接的有效期
        request.setExpiration(DateUtil.getYear(3));
        //返回访问路径
        return client.generatePresignedUrl(request).toString();
    }
    //存储图片资源\对象、音频、视频
    /**
     * 存储内容 上传
     * @param bname 存储空间名称
     * @param key 对象的唯一名称
     * @param data 对象内容
     * @return 访问路径*/
    public static String putData(String bname,String key,byte[] data){
        client.putObject(bname,key,new ByteArrayInputStream(data));
        GeneratePresignedUrlRequest request=new GeneratePresignedUrlRequest(bname,key);
        //设置链接的有效期
        request.setExpiration(DateUtil.getYear(3));
        //返回访问路径
        return client.generatePresignedUrl(request).toString();
    }
    /**
     * 存储内容 上传
     * @param bname 存储空间名称
     * @param key 对象的唯一名称
     * @param endTime  链接的失效期
     * @param data 对象内容
     * @return 访问路径*/
    public static String putData(String bname, String key, Date endTime, byte[] data){
        client.putObject(bname,key,new ByteArrayInputStream(data));
        GeneratePresignedUrlRequest request=new GeneratePresignedUrlRequest(bname,key);
        //设置链接的有效期
        request.setExpiration(endTime);
        //返回访问路径
        return client.generatePresignedUrl(request).toString();
    }
    //生成访问连接
    public static String createUrl(String bname,String key,int seconds){
        GeneratePresignedUrlRequest request=new GeneratePresignedUrlRequest(bname,key);
        //设置链接的有效期
        request.setExpiration(new Date(System.currentTimeMillis()+seconds*1000));
        //返回访问路径
        return client.generatePresignedUrl(request).toString();
    }
    /**
     * 校验key是否存在
     * @param bname 存储空间名称
     * @param key 对象的唯一名称
     * @return 访问路径*/
    public static boolean checkKey(String bname,String key){
        return client.doesObjectExist(bname, key);
    }
    /**
     * 获取存储空间中以指定前缀开头的对象
     * @param bname 存储空间名称
     * @param pre 指定的key的前缀
     * @return 所匹配的keys*/
    public static List<String> list(String bname,String pre){
        // 列举文件。 如果不设置KeyPrefix，则列举存储空间下所有的文件。
        // KeyPrefix，则列举包含指定前缀的文件。
        ObjectListing objectListing = client.listObjects(bname, pre);
        List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
        List<String> list=new ArrayList<>(sums.size());
        for(OSSObjectSummary s:sums){
            list.add(s.getKey());
        }
        return list;
    }
    //删除文件
    public static void delKey(String bname,String key){
        // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        client.deleteObject(bname, key);
    }
}

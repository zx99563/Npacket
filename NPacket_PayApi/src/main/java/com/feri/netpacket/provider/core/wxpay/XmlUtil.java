package com.feri.netpacket.provider.core.wxpay;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-18 17:17
 */
public class XmlUtil {

    //生成xml
    /**
     * 生成xml 字符串
     * @param map 需要生成的数据
     * @return xml格式字符串*/
    public static String createXml(Map<String,String> map){
        //1、创建根节点
        Element root=new Element("xml");
        //2、依次创建子节点
        for(String k:map.keySet()){
            Element child=new Element(k);
            child.setText(map.get(k));
            root.addContent(child);
        }
        //3、创建文档对象
        Document document=new Document(root);
        //4、创建文档输出对象
        XMLOutputter output=new XMLOutputter();
        //5、创建内存输出流
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        try {
            //写出内容到输出流
            output.output(document,baos);
            //转换为字符串
            return new String(baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //解析xml
    /**
     * 解析xml
     * @param strXml xml格式字符串
     * @return 解析的结果的Map集合*/
    public static HashMap<String,Object> parseXml(String strXml){
        HashMap<String,Object> map=new HashMap<>();
        //1.内存流读取
        ByteArrayInputStream bais=new ByteArrayInputStream(strXml.getBytes());
        //2.创建解析器
        SAXBuilder builder=new SAXBuilder();
        try {
            //3、创建文档对象
            Document document=builder.build(bais);
            //4、获取根节点
            Element element=document.getRootElement();
            //5、获取子节点
            List<Element> childs=element.getChildren();
            //6、遍历
            childs.stream().forEach(e->{
                map.put(e.getName(),e.getValue());
            });
            //7、返回
            return map;
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

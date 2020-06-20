package com.feri.netpacket.provider.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-18 11:56
 */
public class QrCodeUtil {
    //生成二维码
    public static void createQrCode(String msg, int width, OutputStream os){
        //1、设置二维码信息
        HashMap<EncodeHintType,Object> map=new HashMap<>();
        //设置纠错等级
        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //设置内容的编码格式
        map.put(EncodeHintType.CHARACTER_SET,"UTF-8");
        //设置外边距 空白区域
        map.put(EncodeHintType.MARGIN,1);
        //2、创建位图
        try {
            BitMatrix matrix=new MultiFormatWriter().encode(msg, BarcodeFormat.QR_CODE,width,width,map);
            //3、设置二维码的颜色 黑白色
            MatrixToImageConfig config=new MatrixToImageConfig(0xFF000000,0xFFFFFFFF);
            //4、写出二维码
            BufferedImage bufferedImage=MatrixToImageWriter.toBufferedImage(matrix,config);
            ImageIO.write(bufferedImage,"png",os);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createQrcodeLogo(String msg, int width, OutputStream os){

    }

    public static void main(String[] args) throws FileNotFoundException {
        createQrCode("我爱Java",400,new FileOutputStream("qecode.png"));
    }
}

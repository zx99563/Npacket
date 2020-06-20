package com.feri.netpacket.provider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feri.common.oss.OssUtil;
import com.feri.common.util.DateUtil;
import com.feri.common.vo.R;
import com.feri.netpacket.entity.resource.ResOSSData;
import com.feri.netpacket.provider.dao.ROSSDataDao;
import com.feri.netpacket.provider.service.OssService;
import com.feri.netpacket.provider.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.IssuerUriCondition;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-09 11:36
 */
@Service
public class OssServiceImpl implements OssService {
    @Value("${npacket.oss.bucket}")
    private String bucketName;
    @Value("${npacket.oss.default-time}")
    private int year;
    @Autowired
    private ROSSDataDao dataDao;
    @Override
    public R fileUp(MultipartFile file) {
        //1.验证上传内容是否为空
        if(!file.isEmpty()){
            //2.获取上传的名称
            String f= FileUtil.renameOSS(bucketName,file.getOriginalFilename());
            //3.基于OSS上传
            try {
                //4构建对象
                ResOSSData data=new ResOSSData();
                data.setEndtime(DateUtil.getYear(year));
                //5.上传资源
                String url= OssUtil.putData(bucketName,f,data.getEndtime(),file.getBytes());
                //6.存储上传记录
                data.setBucket(bucketName);
                data.setCtime(new Date());
                data.setObjkey(f);
                data.setObjurl(url);
                dataDao.insert(data);
                return R.setOK(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return R.setERROR();
    }

    @Override
    public R delFile(String bname, String key) {
        if(OssUtil.checkKey(bname,key)){
            OssUtil.delKey(bname, key);
            dataDao.delKey(bname, key);
            return R.setOK();
        }else {
            return R.setERROR("亲，资源不存在，无法删除");
        }
    }
}

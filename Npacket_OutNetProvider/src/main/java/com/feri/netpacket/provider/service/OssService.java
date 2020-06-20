package com.feri.netpacket.provider.service;

import com.feri.common.vo.R;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-09 11:31
 */
public interface OssService {
    //上传文件
    R fileUp(MultipartFile file);
    //删除文件
    R delFile(String bname,String key);
}
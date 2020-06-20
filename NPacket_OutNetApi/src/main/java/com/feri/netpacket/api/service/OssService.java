package com.feri.netpacket.api.service;

import com.feri.common.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-09 11:59
 */
@FeignClient("OutNetProvider")
public interface OssService {

    //文件上传
    @PostMapping("/ossprovider/fileup.do")
    R fileUp(@RequestPart("file") MultipartFile file);
}

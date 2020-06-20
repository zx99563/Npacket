package com.feri.netpacket.api.controller;

import com.feri.common.vo.R;
import com.feri.netpacket.api.service.OssService;
import feign.Headers;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-09 12:00
 */
@Api
@RestController
public class OssController {
    @Autowired
    private OssService service;
    //文件上传
    @PostMapping("/api/oss/fileup.do")
    public R fileUp(@RequestPart("file") MultipartFile file, HttpServletRequest request){
        return service.fileUp(file);
    }
}

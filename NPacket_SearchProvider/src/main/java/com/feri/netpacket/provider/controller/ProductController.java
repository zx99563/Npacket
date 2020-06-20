package com.feri.netpacket.provider.controller;

import com.feri.common.vo.R;
import com.feri.netpacket.provider.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-11 14:55
 */
@RestController
public class ProductController {
    @Autowired
    private ProductService service;
    @GetMapping("searchprovider/search.do")
    public R search(@RequestParam String msg){
        return service.search(msg);
    }
}

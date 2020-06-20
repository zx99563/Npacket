package com.feri.netpacket.provider.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feri.common.vo.R;
import com.feri.netpacket.entity.sms.SmsLog;
import com.feri.netpacket.provider.service.SmsLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-05 09:49
 */
@RestController
public class SmsLogController {
    @Autowired
    private SmsLogService smsLogService;

    @PostMapping("smsprovider/add.do")
    public R save(@RequestBody SmsLog log){
        if(smsLogService.save(log)){
            return R.setOK();
        }else {
            return R.setERROR();
        }
    }
    //删除
    @DeleteMapping("smsprovider/del.do")
    public R del(int id){
        smsLogService.removeById(id);
        return R.setOK();
    }
    //查询
    @GetMapping("/smsprovider/all.do")
    public R all(){
        return R.setOK(smsLogService.list());
    }
    //分页查询
    @GetMapping("smsprovider/page.do")
    public R page(int page,int size){
        //条件构造器
        QueryWrapper<SmsLog> wrapper=new QueryWrapper<>();
        wrapper.ge("id",1000);
        //分页查询
        List<SmsLog> list=smsLogService.page(new Page<SmsLog>((page-1)*size,size),wrapper).getRecords();
        return R.setOK(list);
    }
}

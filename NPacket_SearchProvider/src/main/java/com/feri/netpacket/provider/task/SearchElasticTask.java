package com.feri.netpacket.provider.task;

import com.feri.netpacket.provider.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-11 14:52
 */
@Component
public class SearchElasticTask {
    @Autowired
    private ProductService service;
    //设置定时任务的 CRON表达式  间隔2小时执行一次
    @Scheduled(cron = "0 0 0/2 * * ?")
    public void syncData(){
        service.syncData();
    }
}

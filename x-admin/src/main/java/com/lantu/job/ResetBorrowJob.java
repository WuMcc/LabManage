package com.lantu.job;

import com.lantu.sys.service.IDeviceService;
import com.lantu.sys.service.ILabService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ResetBorrowJob {

    @Resource
    ILabService labService;
    @Resource
    IDeviceService deviceService;
    @Scheduled(cron = "0/10 * * * * ?")//十秒进行一次
    //@Scheduled(cron = "0 0 0/2 * * ? ")//两小时依次
    public void ResetBorrow(){
        System.out.println("设备和实验室已释放！！！");
        labService.ResetBorrow();
        deviceService.ResetBorrow();
    }
}

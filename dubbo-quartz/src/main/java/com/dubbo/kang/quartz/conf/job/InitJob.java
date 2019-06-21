package com.dubbo.kang.quartz.conf.job;

import com.dubbo.kang.quartz.service.JobService;
import com.dubbo.kang.quartz.util.CronUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author kanghaijun
 * @create 2019/6/21
 * @describe
 */
@Component
public class InitJob implements ApplicationRunner {

    @Autowired
    private JobService jobService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        jobService.deleteJob("com.dubbo.kang.quartz.conf.job.DemoJob","demoJob");
        jobService.addJob("com.dubbo.kang.quartz.conf.job.DemoJob","demoJob", CronUtil.getCron(new DateTime(),0),"这是一个jobDemo");
        System.out.println("*******初始化job*******");
        System.out.println("*******初始化job*******");
        System.out.println("*******初始化job*******");
    }
}

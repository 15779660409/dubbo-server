package com.dubbo.kang.quartz.conf.job;

import com.dubbo.kang.quartz.conf.aspect.JobAnnotation;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author kanghaijun
 * @create 2019/6/21
 * @describe
 */
public class DemoJob implements BaseJob {

    @Override
    @JobAnnotation(className = "com.dubbo.kang.quartz.conf.job.DemoJob",groupName = "demoJob")
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String message = (String) context.getMergedJobDataMap().get("message");
        System.out.println(message);
        System.out.println("自定义任务执行完成");
    }
}

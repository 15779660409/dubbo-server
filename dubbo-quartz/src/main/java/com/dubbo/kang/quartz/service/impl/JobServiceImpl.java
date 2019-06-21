package com.dubbo.kang.quartz.service.impl;

import com.dubbo.kang.quartz.conf.job.BaseJob;
import com.dubbo.kang.quartz.service.JobService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private Scheduler scheduler;

    private static final Logger log = LoggerFactory.getLogger(JobServiceImpl.class);

    /**
     * 添加任务
     *
     * @param jobClassName
     * @param jobGroupName
     * @param cronExpression
     * @return
     * @throws Exception
     */
    @Override
    public void addJob(String jobClassName, String jobGroupName, String cronExpression, String message) throws Exception {

        // 启动调度器
        scheduler.start();
        // 构建job信息
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("message", message);
        JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass())
                .withIdentity(jobClassName, jobGroupName).setJobData(jobDataMap)
                .build();
        // 表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        // 按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName).withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, trigger);
        System.out.println("创建定时任务成功");
    }

    /**
     * 更新定时任务
     *
     * @param jobClassName
     * @param jobGroupName
     * @param cronExpression
     * @return
     * @throws Exception
     */
    @Override
    public void updateJob(String jobClassName, String jobGroupName, String cronExpression) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
            System.out.println("修改定时任务成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("失败" + e.getMessage());
        }
    }

    /**
     * 删除定时任务
     *
     * @param jobClassName
     * @param jobGroupName
     * @return
     * @throws Exception
     */
    @Override
    public void deleteJob(String jobClassName, String jobGroupName) throws SchedulerException {
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
        System.out.println("删除定时任务成功");
    }

    /**
     * 暂停定时任务
     *
     * @param jobClassName
     * @param jobGroupName
     * @return
     * @throws Exception
     */
    @Override
    public void pauseJob(String jobClassName, String jobGroupName) {
        try {
            scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
            System.out.println("暂停定时任务成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("失败" + e.getMessage());
        }
    }

    /**
     * 恢复任务
     *
     * @param jobClassName
     * @param jobGroupName
     * @return
     * @throws Exception
     */
    @Override
    public void resumeJob(String jobClassName, String jobGroupName) {
        try {
            scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
            System.out.println("恢复定时任务成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("失败" + e.getMessage());
        }
    }

    /**
     * @param classname
     * @return
     * @throws Exception
     */
    public static BaseJob getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (BaseJob) class1.newInstance();
    }
}
package com.dubbo.kang.quartz.service;

import org.quartz.SchedulerException;

public interface JobService {

    /**
     * 添加任务
     * @param jobClassName
     * @param jobGroupName
     * @param cronExpression
     * @return
     * @throws Exception
     */
    public void addJob(String jobClassName, String jobGroupName, String cronExpression, String message) throws Exception;

    /**
     * 更新定时任务
     * @param jobClassName
     * @param jobGroupName
     * @param cronExpression
     * @return
     * @throws Exception
     */
    public void updateJob(String jobClassName, String jobGroupName, String cronExpression);

    /**
     * 删除定时任务
     * @param jobClassName
     * @param jobGroupName
     * @return
     * @throws Exception
     */
    public void deleteJob(String jobClassName, String jobGroupName) throws SchedulerException;

    /**
     * 暂停定时任务
     * @param jobClassName
     * @param jobGroupName
     * @return
     * @throws Exception
     */
    public void pauseJob(String jobClassName, String jobGroupName);

    /**
     * 恢复任务
     * @param jobClassName
     * @param jobGroupName
     * @return
     * @throws Exception
     */
    public void resumeJob(String jobClassName, String jobGroupName);

}
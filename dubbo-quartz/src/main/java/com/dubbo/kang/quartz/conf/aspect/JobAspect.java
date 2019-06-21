package com.dubbo.kang.quartz.conf.aspect;

import com.dubbo.kang.quartz.service.JobService;
import com.dubbo.kang.quartz.util.CronUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.joda.time.DateTime;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author kanghaijun
 * @create 2019/6/21
 * @describe
 */
@Aspect
@Component
public class JobAspect {

    @Autowired
    private JobService jobService;

    @Pointcut(value = "@annotation(com.dubbo.kang.quartz.conf.aspect.JobAnnotation)")
    public void pointcut() {
    }

    @After("pointcut()")
    public void updateJob(JoinPoint joinPoint) {
        try {
            Class declaringType = joinPoint.getSignature().getDeclaringType();
            Method method = declaringType.getMethod("execute", JobExecutionContext.class);
            if (!method.isAnnotationPresent(JobAnnotation.class)) {
                return;
            }
            JobAnnotation jobAnnotation = method.getAnnotation(JobAnnotation.class);
            String className = jobAnnotation.className();
            String groupName = jobAnnotation.groupName();
            jobService.updateJob(className,groupName, CronUtil.getCron(new DateTime(),5));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}

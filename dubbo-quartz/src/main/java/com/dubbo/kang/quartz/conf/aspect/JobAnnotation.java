package com.dubbo.kang.quartz.conf.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author kanghaijun
 * @create 2019/6/21
 * @describe
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JobAnnotation {

    String desc() default "更新job";

    String className();

    String groupName();
}

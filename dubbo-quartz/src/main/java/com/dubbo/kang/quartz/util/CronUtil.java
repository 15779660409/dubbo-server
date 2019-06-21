package com.dubbo.kang.quartz.util;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * @author kanghaijun
 * @create 2019/6/19
 * @describe
 */
public class CronUtil {

    /**
     * @param delayMinutes 延时分钟数 为0则不进去延时
     * @return
     */
    public static String getCron(DateTime dataTime, int delayMinutes) {
        if (delayMinutes != 0) {//时间进行延时
            dataTime = dataTime.plusMinutes(delayMinutes);
        }
        String cron = dataTime.toString("ss mm HH dd MM ? yyyy");
        return cron;
    }

    /**
     * 将时间转换成固定格式的日期
     * @param date
     * @param formatter
     * @return
     */
    public static String dataFormatter(Date date,String formatter){
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(formatter);
    }
}

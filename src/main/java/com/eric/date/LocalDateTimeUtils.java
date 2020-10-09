package com.eric.date;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @author shiyu.du
 * @describe 时间相关转换（待补充）
 * @date 2020/9/22
 **/
public class LocalDateTimeUtils {

    /**
     * 获取当前时间时间戳(分钟)
     * @return 返回值
     */
    public static Long getSecondTimestamp(){
        return instant().getEpochSecond();
    }

    /**
     * 获取当前时间时间戳(毫秒)
     * @return 返回值
     */
    public static Long getMilliTimestamp(){
        return instant().toEpochMilli();
    }

    /**
     * 当前时间（格式化）
     * @param format
     * @return
     */
    public static String getNowFormat(String format){
        return getDateTime2Format(LocalDateTime.now(),format);
    }

    /**
     * 将时间戳转换成 LocalDateTime
     * @param timestamp
     * @return
     */
    public static LocalDateTime  getTimestamp2String(long timestamp){
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);

    }

    private static String getDateTime2Format(LocalDateTime dateTime,String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return dateTime.format(formatter);
    }




    private static Instant instant(){
        return LocalDateTime.now().toInstant(ZoneOffset.of("+8"));
    }

}

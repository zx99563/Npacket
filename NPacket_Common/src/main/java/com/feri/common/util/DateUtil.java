package com.feri.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-05 12:02
 */
public class DateUtil {
    //计算今日剩余的有效期
    public static int getLastSeconds(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String d=sdf.format(new Date())+" 23:59:59";
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            //
            Date last=sdf2.parse(d);
            return (int)(last.getTime()-System.currentTimeMillis())/1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
    //
    public static  String getTime(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
    //验证日期是否为昨天
    public static boolean checkDate(Date date){
        //
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        //昨天
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        return sdf.format(date).equals(sdf.format(calendar.getTime()));
    }
    //获取几年后
    public static Date getYear(int year){
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.YEAR,year);
        return calendar.getTime();
    }
}

package com.atguigu.test;

import cn.hutool.core.date.CalendarUtil;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * @author lihw
 * @className TestDate
 * @date 2022-05-18 08:34
 * @description
 */

public class TestDate {
    public static void main(String[] args) throws ParseException {
        testDate();
    }

    public static void testDate() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
//        System.out.println("DateUtil.beginOfHour(date) = " + DateUtil.beginOfHour(date));
//        System.out.println("DateUtil.endOfHour(date) = " + DateUtil.endOfHour(date));
//        System.out.println("---------------------------");
//        System.out.println("DateUtil.beginOfDay(date) = " + DateUtil.beginOfDay(date));
//        System.out.println("DateUtil.endOfDay(date) = " + DateUtil.endOfDay(date));
//        System.out.println("---------------------------");
//        System.out.println("DateUtil.beginOfWeek(date) = " + DateUtil.beginOfWeek(date));
//        System.out.println("DateUtil.endOfWeek(date) = " + DateUtil.endOfWeek(date));
//        System.out.println("---------------------------");
//        System.out.println("DateUtil.beginOfMonth(date) = " + DateUtil.beginOfMonth(date));
//        System.out.println("DateUtil.endOfMonth(date) = " + DateUtil.endOfMonth(date));


        Date beginOfHour = CalendarUtil.beginOfHour(calendar).getTime();
        Date endOfHour = CalendarUtil.endOfHour(calendar).getTime();
        System.out.println("beginOfHour = " + beginOfHour);
        System.out.println("endOfHour = " + endOfHour);
    }
}

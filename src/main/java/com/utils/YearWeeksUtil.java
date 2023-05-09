package com.utils;

import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class YearWeeksUtil {
    /**
     * 获取某年某周的时间跨度
     *
     * @param year
     * @param week
     * @return
     */
    public static Map<String, Long> getWeekRangeMap(int year, int week) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);//设置星期一为一周开始的第一天
        calendar.setMinimalDaysInFirstWeek(7);// 设置当年周最少的天数包含在当年
        int weekYear = calendar.get(Calendar.YEAR);//获得当前的年
        Map<String, Long> map = new HashMap<>();
        calendar.setWeekDate(weekYear, week, Calendar.MONDAY);//获得指定年的第几周的开始日期
        map.put("start", calendar.getTimeInMillis());
        calendar.setWeekDate(weekYear, week, Calendar.SUNDAY);//获得指定年的第几周的结束日期
        map.put("end", calendar.getTimeInMillis());
        return map;
    }

    /**
     * 获取某年有多少周
     *
     * @param year
     * @return
     * @throws ParseException
     */
//
//    public static int getYearWeekConut(int year) {
//        int week = 52;
//        try {
//            Map<String, String> timeMap = getWeekRangeMap(year, 53);
//            if (!CollectionUtils.isEmpty(timeMap)) {
//                String startTime = timeMap.get("startTime");
//                if (startTime.substring(0, 4).equals(year + "")) { //判断年度是否相符，如果相符说明有53个周。
//                    week = 53;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return week;
//    }

    /**
     * 获取某年所有周的时间跨度
     *
     * @param year
     * @return
     */

    public static Map<String, String> getYearWeekMap(int year, int week) {
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getWeekRangeMap(2022, 3));
    }

}
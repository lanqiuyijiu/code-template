package com.jdk.date;

import com.common.pojo.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.*;

/**
 * @author zck created in 2023/3/13 16:14
 */
public class TestDate {
    @Test
    public void test_demo_2023_14_13_16_14_43() {
        System.out.println(LocalTime.now().getSecond());
    }


    @Test
    public void test_2023_03_14_15_31_10() throws InterruptedException {
        User user = new User();
        user.setCreateTime(LocalDateTime.now());
        LocalDateTime createTime = user.getCreateTime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(createTime.format(formatter));
        Thread.sleep(5000);
//        LocalDateTime currentDateTime = LocalDateTime.now();
//        String formattedDateTime = currentDateTime.format(formatter);
        System.out.println(createTime.format(formatter));
    }

    // 获取总周数
    @Test
    public void test_demo_2023_04_27_15_35_19() {
        Calendar c = new GregorianCalendar();
        c.set(2021, Calendar.DECEMBER, 31, 23, 59, 59);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(c.getTime());
        System.out.println(c.get(Calendar.WEEK_OF_YEAR));
    }

    /**
     * 获取当前日期是一年中第几周
     *
     * @param date
     * @return
     */
    public static Integer getWeekOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);

        return c.get(Calendar.WEEK_OF_YEAR);
    }


    /**
     * 获取当前时间是当年的第几周
     */
    @Test
    public void test_demo_2023_06_25_09_56_07() {
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        // 获取一周的起始日期
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        // 计算当前时间是当年的第几周
        int weekNumber = currentDate.get(weekFields.weekOfWeekBasedYear());
        // 输出结果
        System.out.println("当前时间是当年的第 " + weekNumber + " 周");
    }

}

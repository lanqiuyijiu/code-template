package com.jdk.date;

import com.common.pojo.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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



    @Test
    public void test_demo_2023_04_27_15_36_36() {

    }

}

package com.jdk.date.lockDate;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zck created in 2023/5/30 16:31
 * 周的定义
 * 一年的第一周确定
 * 从当年的元旦开始元旦所在的周为第一周（不管元旦当周有几天）
 * 从当月一号开始当天所在周为第一周
 * 如果当月有31天 当月26日为周一 那么26-31日周期算在下个月第一周
 */
public class TestLocalDate {
    @Test
    public void test_demo_2023_05_30_16_32_38() {
        LocalDate firstDayOfYear = LocalDate.of(2022, 1, 1); // 创建表示一年的第一天的LocalDate对象
        LocalDate firstWeekStartDate = firstDayOfYear.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)); // 获取第一周开始日期
        LocalDateTime firstWeekStartDateTime = firstWeekStartDate.atStartOfDay(); // 将日期转换为当天的开始时间，即午夜
        System.out.println("第一周开始日期和时间：" + firstWeekStartDateTime);
    }


    @Test
    public void test_demo_2023_05_30_16_57_47() {
        int year = 2023; // 要判断的年份
        int weekNumber = 13; // 要判断的周数
        LocalDateTime firstDayOfYear = LocalDateTime.of(year, 1, 1, 0, 0); // 创建表示一年的第一天的LocalDateTime对象
        LocalDateTime targetDateTime = firstDayOfYear.plusWeeks(weekNumber - 1); // 获取目标周的日期时间
        int monthValue = targetDateTime.getMonthValue(); // 获取目标周所属的月份
        Month month = targetDateTime.getMonth(); // 获取目标周所属的月份的枚举类型
        int quarter = (monthValue - 1) / 3 + 1; // 计算目标周所属的季度
        System.out.println("第 " + weekNumber + " 周属于 " + monthValue + " 月，第 " + quarter + " 季度");
        System.out.println("月份枚举类型: " + month);
    }


    public void test_demo_2023_05_31_14_49_19(int year, int weekNumber) {
        LocalDate firstDayOfYear = LocalDate.of(year, 1, 1); // 创建表示一年的第一天的LocalDate对象
        LocalDate firstDayOfWeek = firstDayOfYear.with(TemporalAdjusters.previousOrSame(WeekFields.ISO.getFirstDayOfWeek())); // 获取一年中的第一周的开始日期
        LocalDateTime targetWeekStart = firstDayOfWeek.plusWeeks(weekNumber - 1).atStartOfDay(); // 获取目标周的开始日期
        LocalDateTime targetWeekEnd = targetWeekStart.plusDays(6).withHour(23).withMinute(59).withSecond(59);; // 获取目标周的结束日期
//        LocalDateTime start = targetWeekStart.atStartOfDay(); // 将开始日期转换为当天的开始时间，即午夜
//        LocalDateTime end = targetWeekEnd.atTime(23, 59, 59); // 将结束日期转换为当天的结束时间，即23:59:59
        System.out.println(targetWeekStart);
        System.out.println(targetWeekEnd);
    }

    @Test
    public void test_demo_2023_05_31_15_42_32() {
        int year = 2023; // 要生成周数的年份
        LocalDate startOfYear = LocalDate.of(year, 1, 1);
        LocalDate endOfYear = LocalDate.of(year, 12, 31);
        LocalDate current = startOfYear;
        int i = 1;
        while (current.until(endOfYear,ChronoUnit.DAYS) > 6) {
            int month = current.with(DayOfWeek.SUNDAY).getMonthValue();
            int quarter = (month - 1) / 3 + 1;
            test_demo_2023_05_31_14_49_19(year, i);
            System.out.println("周数: " + i + "，月份: " + month + "，季度: " + quarter);
            current = current.plusWeeks(1);
            i++;
        }
    }

    @Test
    public void test_demo_2023_05_31_18_11_40() {
        for (int i = 2015; i < 2050 ; i++) {
            System.out.println(LocalDate.of(i, 1, 1).get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));; // 一年的起始日期

        }
    }

}

















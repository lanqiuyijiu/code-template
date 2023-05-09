package com.jdk.net.http;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zck created in 2023/4/6 18:10
 */
public class Demo {
    @Test
    public void test_demo_2023_04_06_18_10_19() {
//        System.out.println( HttpUtil.doGet("https://mobile.zhgz.hzgzw.gov.cn/#/"));
        LocalDate localDate = LocalDate.of(2023, 1, 1);

        LocalDateTime start = LocalDateTime
                .of(localDate.plusWeeks(18).minusDays(localDate.getDayOfWeek().getValue() - 1), LocalTime.MIN);
        LocalDateTime end = LocalDateTime
                .of(localDate.plusWeeks(18).plusDays(7 - localDate.getDayOfWeek().getValue()), LocalTime.MAX);
        Map<String, Long> map = new HashMap<>();
        map.put("start", start.toInstant(ZoneOffset.of("+8")).toEpochMilli());
        map.put("end", end.toInstant(ZoneOffset.of("+8")).toEpochMilli());
        System.out.println(map);
    }
}

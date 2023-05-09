package com.jdk;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author zck created in 2023/3/16 18:03
 */
public class TestString {
    @Test
    public void test_2023_03_16_18_03_14() {
        String str = "123456789";
        System.out.println(str.substring(0, 4));
        System.out.println(str);
    }

    @Test
    public void test_2023_03_17_13_58_56() {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(null);
        System.out.println();
    }

    @Test
    public void test_demo_2023_04_07_18_43_27() {
        System.out.println(UUID.randomUUID().toString());
    }
}

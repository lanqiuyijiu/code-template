package com.lombok;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

/**
 * @author zck created in 2023/3/27 15:11
 */
public class TestGetAndSet {
    @Test
    public void test_demo_2023_03_27_15_12_45() {
        // 不会覆盖自己写的getset
        Lombok lombok = new Lombok();
        lombok.setId(10000L);
        System.out.println(lombok.getId());
    }

    @Test
    public void test_demo_2023_03_30_16_22_16() {
        LinkedList<Object> objects = new LinkedList<>();
        objects.poll();
        System.out.println(0/1);
    }
}

package com.utils;

import org.junit.jupiter.api.Test;

/**
 * @author zck created in 2023/7/7 16:07
 */
public class TestBcrypt {

    @Test
    public void test_demo_2023_07_07_16_07_24() {

        BCryptEncoder bCryptEncoder = new BCryptEncoder();
        System.out.println( bCryptEncoder.matches("admin.1123", "$2a$10$7B.ssX1ypUfgMkfrSU7IQ.h32UfYE3z0qzEJJLqrSyM9pPrg4M.72"));

    }

}

package com.common;

import com.common.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.ArrayList;

public class CommonTest {

    final ArrayList a = new ArrayList<String>();

    @Test
    @Transactional

    public void test_demo_2023_03_27_23_07_03() {
        User user = new User();
        User user1 = new User();
        user1.setUserName("张三");
        user.setUserName("张三");
        System.out.println(user == user1);
        System.out.println(user.equals(user1));

    }
}

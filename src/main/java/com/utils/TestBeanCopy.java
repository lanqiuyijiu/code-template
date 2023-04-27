package com.utils;

import com.common.pojo.User;
import com.common.pojo.UserVO;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;

import java.time.LocalDateTime;

/**
 * @author zck created in 2023/3/15 15:08
 */
public class TestBeanCopy {
    @Test
    public void test_2023_03_15_15_09_04() {
        User user = new User();
        user.setCreateTime(LocalDateTime.now());
        user.setEmail("123.456");
        user.setUserName("test");
        UserVO userVO = new UserVO();
//        BeanCopier beanCopier = BeanCopier.create(user.getClass(), userVO.getClass(), false);
//        beanCopier.copy(user, userVO, null);

        BeanUtils.copyProperties(user,userVO);
        System.out.println();
    }
}

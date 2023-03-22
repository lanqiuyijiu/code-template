package com.common.pojo;

import lombok.Data;

import java.time.LocalDateTime;


/**
 * @author zck created in 2023/3/13 15:57
 */
@Data
public class User {
    private String userName;
    private String password;
    private String phone;
    private String email;
    private Integer userType;
    private LocalDateTime createTime;
}


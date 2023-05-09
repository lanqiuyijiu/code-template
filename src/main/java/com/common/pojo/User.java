package com.common.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


/**
 * @author zck created in 2023/3/13 15:57
 */
@Getter
@Setter
public class User {
    private String userName;
    private String password;
    private String phone;
    private String email;
    private Integer userType;
    private LocalDateTime createTime;
}


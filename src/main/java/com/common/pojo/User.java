package com.common.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/**
 * @author zck created in 2023/3/13 15:57
 */
@Getter
@Setter
public class User {
    private Long regionId;
    private String regionName;
    private String userName;
    private String password;
    private String phone;
    private Integer userType;
}


package com.common.pojo;

import lombok.Data;


/**
 * @author zck created in 2023/3/13 15:57
 */
@Data
public class User {
    private Long regionId;
    private String regionName;
    private String userName;
    private String password;
    private String phone;
    private Integer userType;
}


package com.lombok;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zck created in 2023/3/27 15:11
 */
@Getter
@Setter
@ToString
public class Lombok {
    private Long id;
    private Long time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id * 1000;
    }
}


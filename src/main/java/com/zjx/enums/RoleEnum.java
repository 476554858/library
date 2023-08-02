package com.zjx.enums;

import lombok.Data;

public enum RoleEnum {
    READER(0, "读者"),
    ADMIN(1, "管理员");

    private Integer code;

    private String desc;

    RoleEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}

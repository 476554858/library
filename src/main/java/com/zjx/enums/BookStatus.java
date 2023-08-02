package com.zjx.enums;

public enum BookStatus {
    UN_BORROWED(0, "未借"),
    BORROWED(1, "已借"),
    LOSE(2, "丢失");

    private Integer code;

    private String desc;

    BookStatus(Integer code, String desc) {
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

package com.zjx.common;

import lombok.Data;

@Data
public class Result<T> {

    /**
     * 0:成功,-1:失败
     */
    private Integer code;

    private String msg;

    private T data;

    public Result() {
        this.code = 0;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> Result<T> success(String msg){
        return new Result<T>(0, msg);
    }

    public static <T> Result<T> failed(String msg){
        return new Result<T>(-1, msg);
    }
}

package com.zjx.common;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

    /**
     * 0:成功,1:失败
     */
    private Integer code;

    private String msg;

    private Long count;

    private List<T> data;

    public PageResult(Long count, List<T> data) {
        this.count = count;
        this.data = data;
        this.code = 0;
    }

    public PageResult(String msg) {
        this.code = 1;
        this.msg = msg;
    }

    public static <T> PageResult<T> success(Long count, List<T> data){
        return new PageResult<T>(count, data);
    }

    public static <T> PageResult<T> failed(String msg){
        return new PageResult<T>(msg);
    }
}

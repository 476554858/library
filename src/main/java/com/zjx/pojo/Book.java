package com.zjx.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 图书
 */
@Data
@TableName("t_book")
public class Book {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 数名
     */
    private String bookName;

    /**
     * 作者
     */
    private String author;

    /**
     * 分类
     */
    private Integer category;

    /**
     * 出版社
     */
    private String press;

    /**
     * 0.未借, 1.已借, 2.丢失
     */
    private Integer status;

}

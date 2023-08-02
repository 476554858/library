package com.zjx.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 借书记录
 */
@Data
@TableName("t_lend_book_record")
public class LendBookRecord {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer bookId;

    private Integer userId;

    /**
     * 借书日期
     */
    private Date lendDate;

    /**
     * 应归还日期
     */
    private Date revertDate;
}

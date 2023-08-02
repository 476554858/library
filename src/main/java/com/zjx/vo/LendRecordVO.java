package com.zjx.vo;

import com.zjx.pojo.Book;
import lombok.Data;

import java.util.Date;

@Data
public class LendRecordVO extends Book {

    private Integer bookId;

    private Date revertDate;

    private Date lendDate;

    private String nickName;
}

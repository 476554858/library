package com.zjx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjx.pojo.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
}

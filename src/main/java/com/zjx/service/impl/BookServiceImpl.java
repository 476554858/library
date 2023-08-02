package com.zjx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjx.enums.BookStatus;
import com.zjx.mapper.BookMapper;
import com.zjx.mapper.LendBookRecordMapper;
import com.zjx.pojo.Book;
import com.zjx.pojo.LendBookRecord;
import com.zjx.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private LendBookRecordMapper recordMapper;

    @Transactional
    public void borrowBook(Integer bookId, Integer userId){
        Book book = bookMapper.selectById(bookId);
        //修改图书状态
        book.setStatus(BookStatus.BORROWED.getCode());
        bookMapper.updateById(book);

        //新增借书记录
        LendBookRecord record = new LendBookRecord();
        record.setBookId(bookId);
        record.setUserId(userId);
        record.setLendDate(new Date());
        //默认能借书10天
        record.setRevertDate(getRevertDate(10));
        recordMapper.insert(record);
    }

    @Transactional
    public void revertBook(Integer bookId, Integer userId){
        Book book = bookMapper.selectById(bookId);
        //修改为未借状态
        book.setStatus(BookStatus.UN_BORROWED.getCode());
        bookMapper.updateById(book);

        //删除借书记录
        QueryWrapper<LendBookRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId)
                .eq("book_id", bookId);
        recordMapper.delete(wrapper);
    }

    /**
     * 获取应归还日期
     * @param dayNum
     * @return
     */
    private Date getRevertDate(Integer dayNum){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, dayNum);
        return calendar.getTime();
    }
}

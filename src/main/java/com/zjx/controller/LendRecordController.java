package com.zjx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjx.common.PageResult;
import com.zjx.common.Result;
import com.zjx.common.UserContext;
import com.zjx.enums.BookStatus;
import com.zjx.mapper.BookMapper;
import com.zjx.mapper.LendBookRecordMapper;
import com.zjx.pojo.Book;
import com.zjx.pojo.LendBookRecord;
import com.zjx.pojo.User;
import com.zjx.vo.LendRecordVO;
import com.zjx.vo.PageVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;

@ResponseBody
@Controller
public class LendRecordController {

    @Autowired
    private LendBookRecordMapper recordMapper;

    @Autowired
    private BookMapper bookMapper;

    /**
     * 查看我的借书记录
     * @param pageVO
     * @param book
     * @return
     */
    @RequestMapping("/lend/record/list")
    public PageResult getLendRecord(PageVO pageVO, Book book){
        Page<LendRecordVO> page = new Page<>(pageVO.getPage(), pageVO.getLimit());

        QueryWrapper<LendRecordVO> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", UserContext.getUserId());
        if(StringUtils.isNotBlank(book.getBookName())){
            wrapper.eq("book_name", book.getBookName());
        }
        if(StringUtils.isNotBlank(book.getAuthor())){
            wrapper.like("author", book.getAuthor());
        }
        if(StringUtils.isNotBlank(book.getPress())){
            wrapper.like("press", book.getPress());
        }
        Page<LendRecordVO> recordVOPage = recordMapper.findLendRocord(page, wrapper);
        PageResult<LendRecordVO> pageResult = PageResult.success(recordVOPage.getTotal(), recordVOPage.getRecords());
        return pageResult;
    }

    /**
     * 查看用户的借书记录
     * @param pageVO
     * @param bookName
     * @param nickName
     * @return
     */
    @RequestMapping("/lend/recordWithUser/list")
    public PageResult getLendRecordWithUser(PageVO pageVO, String bookName, String nickName){
        Page<LendRecordVO> page = new Page<>(pageVO.getPage(), pageVO.getLimit());

        QueryWrapper<LendRecordVO> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(bookName)){
            wrapper.eq("book_name", bookName);
        }
        if(StringUtils.isNotBlank(nickName)){
            wrapper.like("nick_name", nickName);
        }
        Page<LendRecordVO> recordVOPage = recordMapper.findLendRocordWithUser(page, wrapper);
        PageResult<LendRecordVO> pageResult = PageResult.success(recordVOPage.getTotal(), recordVOPage.getRecords());
        return pageResult;
    }

    /**
     * 续借
     * @param bookId
     * @return
     */
    @RequestMapping("/lend/again")
    public Result addBook(Integer bookId){
        QueryWrapper<LendBookRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("book_id", bookId)
                .eq("user_id", UserContext.getUserId());
        LendBookRecord record = recordMapper.selectOne(wrapper);
        //每次续借10天
        record.setRevertDate(addDays(record.getRevertDate(),10));
        if(checkRevertDate(record)){
            return Result.failed("已借书超过30天,不能续借");
        }

        recordMapper.updateById(record);
        return Result.success("续借成功");
    }

    /**
     * 图书遗失
     * @param bookId
     * @return
     */
    @RequestMapping("/book/loss")
    public Result bookLoss(Integer bookId){
        Book book = bookMapper.selectById(bookId);
        if(book.getStatus() == BookStatus.LOSE.getCode()){
            return Result.success("已遗失");
        }
        book.setStatus(BookStatus.LOSE.getCode());
        bookMapper.updateById(book);
        return Result.success("操作成功");
    }

    /**
     * 获取新的续借日期
     * @param revertDate
     * @param dayNum
     * @return
     */
    private Date addDays(Date revertDate, Integer dayNum){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(revertDate);
        calendar.add(Calendar.DAY_OF_YEAR, dayNum);
        return calendar.getTime();
    }

    /**
     * 查看相隔天数
     * @param record
     * @return
     */
    private Boolean checkRevertDate(LendBookRecord record){
        Date lendDate = record.getLendDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lendDate);

        int lendDay = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.setTime(record.getRevertDate());
        int revertDay = calendar.get(Calendar.DAY_OF_YEAR);
        return (revertDay - lendDay) >= 40;
    }


}

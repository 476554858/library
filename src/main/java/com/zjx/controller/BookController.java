package com.zjx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjx.common.PageResult;
import com.zjx.common.Result;
import com.zjx.common.UserContext;
import com.zjx.enums.BookStatus;
import com.zjx.mapper.BookMapper;
import com.zjx.pojo.Book;
import com.zjx.pojo.User;
import com.zjx.service.BookService;
import com.zjx.vo.PageVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@ResponseBody
@Controller
public class BookController {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookService bookService;

    /**
     * 图书查看
     * @param pageVO
     * @param book
     * @return
     */
    @RequestMapping("/book/list")
    public PageResult getBookList(PageVO pageVO, Book book){
        Page<Book> page = new Page<>(pageVO.getPage(), pageVO.getLimit());
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        //查询未借状态的书
        wrapper.eq("status", BookStatus.UN_BORROWED.getCode());
        if(StringUtils.isNotBlank(book.getBookName())){
            wrapper.like("book_name", book.getBookName());
        }
        if(StringUtils.isNotBlank(book.getAuthor())){
            wrapper.like("author", book.getAuthor());
        }
        if(StringUtils.isNotBlank(book.getPress())){
            wrapper.like("press", book.getPress());
        }
        if(book.getCategory() != null){
            wrapper.eq("category", book.getCategory());
        }
        Page<Book> bookPage = bookMapper.selectPage(page, wrapper);
        PageResult<Book> pageResult = PageResult.success(bookPage.getTotal(), bookPage.getRecords());
        return pageResult;
    }

    /**
     *  图书添加
     * @param book
     * @return
     */
    @RequestMapping("/book/add")
    public Result addBook(Book book){
        if(book.getId() == null){
            //默认是未借状态
            book.setStatus(BookStatus.UN_BORROWED.getCode());
            bookMapper.insert(book);
            return Result.success("新增成功");
        }
        bookMapper.updateById(book);
        return Result.success("更新成功");
    }

    @RequestMapping("/book/delete")
    public Result delBook(Integer id){
        bookMapper.deleteById(id);
        return Result.success("删除成功");
    }

    /**
     * 借书
     * @param bookId
     * @return
     */
    @RequestMapping("/book/borrow")
    public Result borrowBook(Integer bookId){
        bookService.borrowBook(bookId, UserContext.getUserId());
        return Result.success("借阅成功");
    }

    /**
     * 还书
     * @param bookId
     * @return
     */
    @RequestMapping("/book/revert")
    public Result revertBook(Integer bookId){
        bookService.revertBook(bookId, UserContext.getUserId());
        return Result.success("还书成功");
    }



}



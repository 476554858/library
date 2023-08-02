package com.zjx.service;

public interface BookService {

    /**
     * 借书
     * @param bookId
     * @param userId
     */
    public void borrowBook(Integer bookId, Integer userId);

    /**
     * 还书
     * @param bookId
     * @param userId
     */
    public void revertBook(Integer bookId, Integer userId);
}

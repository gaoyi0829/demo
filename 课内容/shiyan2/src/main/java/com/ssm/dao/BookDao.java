package com.ssm.dao;

import com.ssm.entity.Book;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao {
    Book queryById(@Param("book_id") long book_id);

    List<Book> queryAll(@Param("offset") int offset, @Param("limit") int limit);

    int reduceNumber(long bookId);

    void delBook(@Param("bookId") long bookId);

    void insBook(@Param("name") String name, @Param("number") Integer number);
}

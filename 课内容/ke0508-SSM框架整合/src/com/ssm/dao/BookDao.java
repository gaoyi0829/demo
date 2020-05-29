package com.ssm.dao;

import com.ssm.entity.Book;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookDao {
    @Select("select book_id,name,number from book where book_id=#{0}")
    Book queryById(long id);
    @Select("select book_id,name,number from book oder by book_id limit #{offset},#{limit}")
    List<Book> queryAll(@Param("offset") int offset,@Param("limit") int limit);
    @Update("update book set number=number-1 where book_id=#{0} and number>0")
    int reduceNumber(long bookId);
}

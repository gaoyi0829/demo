package com.ssm.service;

import com.ssm.entity.Appointment;
import com.ssm.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookService {
    Book getById(long bookId);

    List<Book> getList();

    void delBook(long bookId);

    void insBook(String bname, Integer bnum);

    int reduceNumber(long bookId);
}


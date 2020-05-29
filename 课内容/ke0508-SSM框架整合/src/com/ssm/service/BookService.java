package com.ssm.service;

import com.ssm.entity.Appointment;
import com.ssm.entity.Book;

import java.util.List;
public interface BookService {
    Book getById(long bookId);
    List<Book> getList();
    Appointment appoint(long bookId, long studentId);
}

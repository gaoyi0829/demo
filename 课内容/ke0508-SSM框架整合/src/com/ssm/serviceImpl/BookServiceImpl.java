package com.ssm.serviceImpl;

import com.ssm.dao.AppointmentDao;
import com.ssm.dao.BookDao;
import com.ssm.entity.Appointment;
import com.ssm.entity.Book;
import com.ssm.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value = "BookService")
public class BookServiceImpl implements BookService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BookDao bookDao;
    @Autowired
    private AppointmentDao appointmentDao;
    @Override
    public Book getById(long bookId) {
        return bookDao.queryById(bookId);
    }

    @Override
    public List<Book> getList() {
        return bookDao.queryAll(0,1);
    }

    @Override
    public Appointment appoint(long bookId, long studentId) {
        return appointmentDao.queryByKeyWithBook(bookId,studentId);
    }
}

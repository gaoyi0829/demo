package com.ssm.serviceimpl;

import com.ssm.dao.AppointmentDao;
import com.ssm.dao.BookDao;
import com.ssm.entity.Appointment;
import com.ssm.entity.Book;
import com.ssm.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service(value = "BookService")
public class BookServiceImpl implements BookService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BookDao bookDao;

    public Book getById(long bookId) {
        return bookDao.queryById(bookId);
    }

    public List<Book> getList() {
        return bookDao.queryAll(0, 5);
    }

    public void delBook(long bookId) {
        bookDao.delBook(bookId);
    }

    public void insBook(String bname, Integer bnum) {
        bookDao.insBook(bname, bnum);
    }

    public int reduceNumber(long bookId) {
        return bookDao.reduceNumber(bookId);
    }

}

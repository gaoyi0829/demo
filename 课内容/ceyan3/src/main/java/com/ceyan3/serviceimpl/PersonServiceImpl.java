package com.ceyan3.serviceimpl;

import com.ceyan3.dao.PersonDao;
import com.ceyan3.entity.*;
import com.ceyan3.service.PersonService;
import org.apache.ibatis.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service(value = "PersonService")
public class PersonServiceImpl implements PersonService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PersonDao personDao;

    public List<Person> select(String name) {
        return personDao.select(name);
    }

    public void updata(Person person) {
        personDao.updata(person);
    }

//    public New getById(long bookId) {
//        return bookDao.queryById(bookId);
//    }
}

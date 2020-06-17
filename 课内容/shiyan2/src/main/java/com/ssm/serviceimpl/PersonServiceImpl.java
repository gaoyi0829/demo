package com.ssm.serviceimpl;

import com.ssm.dao.PersonDao;
import com.ssm.entity.*;
import com.ssm.service.PersonService;
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

    public List<Person> selectbyid(Integer id) {
        return personDao.selectbyid(id);
    }

    public void updata(Integer id, String varname, String val) {
        personDao.updata(id,varname,val);
    }


//    public New getById(long bookId) {
//        return bookDao.queryById(bookId);
//    }
}

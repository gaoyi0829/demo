package org.example.serviceimpl;

import org.example.dao.Dao;
import org.example.entity.*;
import org.example.service.NewService;
import org.apache.ibatis.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service(value = "NewService")
public class ServiceImpl implements NewService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private Dao bookDao;

//    public New getById(long bookId) {
//        return bookDao.queryById(bookId);
//    }
}

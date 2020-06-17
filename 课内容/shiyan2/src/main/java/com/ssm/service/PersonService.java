package com.ssm.service;

import com.ssm.entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {
    List<Person> select(String name);
    List<Person> selectbyid(Integer id);
    void updata(Integer id,String varname,String val);
}

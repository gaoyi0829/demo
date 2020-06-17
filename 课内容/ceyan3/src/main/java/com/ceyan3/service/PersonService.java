package com.ceyan3.service;

import com.ceyan3.entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {
    List<Person> select(String name);
    void updata(Person persion);
}

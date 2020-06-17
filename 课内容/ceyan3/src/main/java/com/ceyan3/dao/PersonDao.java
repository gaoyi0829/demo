package com.ceyan3.dao;

import com.ceyan3.entity.Person;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonDao {
    List<Person> select(@Param("name") String name);
    void updata(Person person);
}

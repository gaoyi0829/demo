package com.ssm.dao;

import com.ssm.entity.Person;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonDao {
    public List<Person> select(@Param("name") String name);
    public List<Person> selectbyid(@Param("id") Integer id);
    void updata(@Param("id") Integer id,@Param("varname") String varname,@Param("val") String value);
}

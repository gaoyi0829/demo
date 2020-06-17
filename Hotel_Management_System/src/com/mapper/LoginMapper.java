package com.mapper;

import com.pojo.Customer;
import com.pojo.Manager;
import org.apache.ibatis.annotations.Param;

public interface LoginMapper {
    public Customer logincus(@Param("number") String cus_id);
    public Manager loginman(@Param("jobnum") String cus_id);
    public void remaninfo(@Param("jobnum") String jobnum,@Param("man_name") String man_name,@Param("password") String password);
    public void regist(Customer customer);
}

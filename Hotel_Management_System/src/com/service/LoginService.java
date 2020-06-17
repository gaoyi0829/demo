package com.service;

import com.mapper.LoginMapper;
import com.pojo.Customer;
import com.pojo.Manager;

public class LoginService {
    private LoginMapper loginMapper;

    public LoginMapper getLoginMapper() {
        return loginMapper;
    }

    public void setLoginMapper(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }
    public Customer logincus(String cus_id){
        return loginMapper.logincus(cus_id);
    }
    public Manager loginman(String cus_id){
        return loginMapper.loginman(cus_id);
    }
    public void remaninfo(String cus_id,String man_name,String password){
       loginMapper.remaninfo(cus_id,man_name,password);
    }
    public void regist(Customer customer){
        loginMapper.regist(customer);
    }
}

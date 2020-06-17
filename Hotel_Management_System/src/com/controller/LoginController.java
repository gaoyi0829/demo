package com.controller;

import com.pojo.Customer;
import com.pojo.Manager;
import com.service.LoginService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    LoginService ls = ac.getBean("LoginService", LoginService.class);
    private Customer customer;
    private Manager manager;

    @RequestMapping(value = "login", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String login(String cus_id, String password, String type) {
        if (type.equals("用户")) {
            customer = ls.logincus(cus_id);
            if (customer != null && !customer.equals("") && !customer.equals("null")) {
                if (customer.getCus_password().equals(password)) {
                    return customer.getCus_name();
                } else if (!customer.getCus_password().equals(password)) {
                    return "密码错误!";
                }
            }
        } else if (type.equals("管理员")) {
            manager = ls.loginman(cus_id);
            if (manager != null && !manager.equals("") && !manager.equals("null")) {
                if (manager.getPassword().equals(password)) {
                    return manager.getMan_name();
                } else if (!manager.getPassword().equals(password)) {
                    return "密码错误!";
                }
            }
        }
        System.out.println("3");
        return "账号不存在!";
    }

    @RequestMapping(value = "remaninfo", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public void remaninfo(String manid, String manname, String password) {
        ls.remaninfo(manid, manname, password);
    }

    @RequestMapping(value = "regist", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String regist(String cus_name, String number, String cus_password, String question, String answer, String telephone) {
        Customer cus = new Customer(cus_name, number, cus_password, question, answer, telephone);
        customer = ls.logincus(cus.getNumber());
        if (customer != null && !customer.equals("") && !customer.equals("null")) {
            return "身份证号已存在!请重新注册或登录!";
        } else {
            ls.regist(cus);
        }
        return "注册成功!";
    }
}

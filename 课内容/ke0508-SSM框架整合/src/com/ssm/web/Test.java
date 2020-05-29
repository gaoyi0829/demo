package com.ssm.web;

import com.ssm.entity.Book;
import com.ssm.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class Test {
    @Autowired
    private BookService bs;
    @RequestMapping("book")
    public void test() throws Exception {
        System.out.println("1");
        System.out.println(bs.getById(1001));
    }
}

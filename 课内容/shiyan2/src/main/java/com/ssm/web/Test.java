package com.ssm.web;

import com.ssm.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Test {
    @Qualifier("BookService")
    @Autowired
    private BookService bs;
    @RequestMapping("book")
    public void test() throws Exception {
        System.out.println("ssm查询:"+bs.getById(1001));
        bs.insBook("星火英语",10);
        System.out.println("插入完成！");
        System.out.println("ssm插入:"+bs.getById(1004));
        bs.reduceNumber(1001);
        System.out.println("ssm修改:"+bs.getById(1001));
        bs.delBook(1004);
        System.out.println("ssm删除:"+bs.getList());
    }
}

package com.test.controller;

import com.test.service.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NewController {
    @Qualifier("NewService")
    @Autowired
    private NewService ns;
    @RequestMapping("new")
    public void test() throws Exception {
    }
}

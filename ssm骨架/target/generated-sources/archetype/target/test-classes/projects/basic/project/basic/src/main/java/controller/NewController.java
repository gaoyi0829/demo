package it.pkg.controller;

import it.pkg.service.NewService;
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

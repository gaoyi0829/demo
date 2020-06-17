package com.ceyan3.controller;

import com.ceyan3.entity.Person;
import com.ceyan3.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {
    @Qualifier("PersonService")
    @Autowired
    private PersonService ps;
    List<Person> plist = new ArrayList<Person>();
    @RequestMapping("select")
    @ResponseBody
    public List<Person> select(String name) throws Exception {
        plist = ps.select(name);
        
        return plist;
    }
}

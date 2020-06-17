package com.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.ssm.entity.Person;
import com.ssm.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PersonController {
    @Qualifier("PersonService")
    @Autowired
    private PersonService ps;
    private List<Person> plist;
    @RequestMapping("select")
    @ResponseBody
    public List<Person> select(String name) throws Exception {
        plist = ps.select(name);
        //String json = JSON.toJSONString(plist);
        return plist;
    }
    @RequestMapping("selectbyid")
    @ResponseBody
    public List<Person> selectbyid(Integer id) throws Exception {
        plist = ps.selectbyid(id);
        //String json = JSON.toJSONString(plist);
        return plist;
    }
    @RequestMapping("updata")
    @ResponseBody
    public String updata(Integer id,String varname,String val) throws Exception {
        ps.updata(id,varname,val);
        return "";
    }
}

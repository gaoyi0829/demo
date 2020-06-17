#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package com.${artifactId}.controller;

import com.${artifactId}.service.NewService;
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

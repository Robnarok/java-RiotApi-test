package com.robnarok.yoneban.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Webcontroller {

    @Value("${foobar}")
    String foo;

    @ResponseBody
    @GetMapping("/")
    public String mainpage(){
        return foo;
    }
}

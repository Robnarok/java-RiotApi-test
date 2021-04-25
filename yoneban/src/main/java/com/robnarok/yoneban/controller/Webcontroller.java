package com.robnarok.yoneban.controller;

import com.robnarok.yoneban.repository.PersistentMatchRepository;
import com.robnarok.yoneban.services.ApiFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Webcontroller {

    @Autowired
    ApiFetcher apiFetcher;

    @Autowired
    PersistentMatchRepository persistenMatchRepository;

    @ResponseBody
    @GetMapping("/")
    public String mainpage(){



        return "foo";
    }
}

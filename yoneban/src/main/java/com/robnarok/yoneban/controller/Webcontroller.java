package com.robnarok.yoneban.controller;

import com.robnarok.yoneban.dto.SummonerDTO;
import com.robnarok.yoneban.services.ApiFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Webcontroller {

    @Autowired
    ApiFetcher apiFetcher;

    @ResponseBody
    @GetMapping("/")
    public String mainpage(){



        return "foo";
    }
}

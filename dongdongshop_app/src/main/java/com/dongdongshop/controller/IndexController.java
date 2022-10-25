package com.dongdongshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"toindex", "/"})
    public String toindex() {
        return "index";
    }

    @RequestMapping("/toPay")
    public String toPay(){
        return "pay";
    }

}

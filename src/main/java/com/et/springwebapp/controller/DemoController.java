package com.et.springwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/demo/")
public class DemoController {

    @RequestMapping(value = "index")
    public String index() {
        System.out.println("aaaaaaaaaa");
        return "demo/index";
    }
}
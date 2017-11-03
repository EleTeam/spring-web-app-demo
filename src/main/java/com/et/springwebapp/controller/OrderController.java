package com.et.springwebapp.controller;

import com.et.springwebapp.entity.User;
import com.et.springwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

@Controller
@RequestMapping(value = "/order/")
public class OrderController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "index")
    public String index() {
        System.out.println("aaaaaaaaaa");
        return "user/index";
    }

    @RequestMapping(value = "add")
    public String add() {
        User student = new User();
        student.setName("name-" + new Random().toString());
        userService.add(student);
        return "user/add";
    }
}
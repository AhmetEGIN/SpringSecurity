package com.egin.springsecurity_2023_c6_e1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {


    @GetMapping("/test1")
    public String test1(){
        return "test1";
    }


    @GetMapping("/test2")
    public String test2(){
        return "GET: test2";
    }

    @PostMapping("/test2")
    public String test4(){
        return "PUT: test2";
    }


    @GetMapping("/test3")
    public String test3(){
        return "test3";
    }

}

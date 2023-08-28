package com.egin.springsecurity_2023_c5_e1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {


    @GetMapping("/demo")
    public String demo(){
        return "Demo";
    }


    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}

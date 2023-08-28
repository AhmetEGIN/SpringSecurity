package com.egin.springsecurity_2023_c3_e1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("hello")
    public String demo() {

        return "Hello";

    }

}

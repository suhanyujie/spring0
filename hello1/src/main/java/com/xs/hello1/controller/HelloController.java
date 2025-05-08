package com.xs.hello1.controller;

import com.xs.hello1.conf.tron.TronBlockConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class HelloController {

    @Autowired
    TronBlockConfigProperties tronBlockConfigProperties;

    @GetMapping("/hello")
    public String hello(){
        System.out.printf("%s\n", tronBlockConfigProperties.getNet());
        return "hello";
    }
}

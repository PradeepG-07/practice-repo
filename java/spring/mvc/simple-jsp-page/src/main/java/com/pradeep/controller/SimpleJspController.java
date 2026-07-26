package com.pradeep.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SimpleJspController {
    @GetMapping
    public String handleSimpleJSP(){
        System.out.println("Controller is reached");
        return "index";
    }
}

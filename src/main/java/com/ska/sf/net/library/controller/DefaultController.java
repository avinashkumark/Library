package com.ska.sf.net.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping("/")
    public String home1() {
        return "/home";
    }

    @GetMapping("/home")
    public String home2() {
        return "/home";
    }

    @GetMapping("/login")
    public String home3() {
        return "/home";
    }
}

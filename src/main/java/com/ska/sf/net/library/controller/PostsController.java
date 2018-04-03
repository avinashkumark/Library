package com.ska.sf.net.library.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostsController {

    @RequestMapping("/post/list")
    @Secured("ROLE_USER")
    public List<String> getNamesList() {
        List<String> names = new ArrayList<>();
        names.add("avinash - 1");
        names.add("avinash - 2");
        names.add("avinash - 3");
        names.add("avinash - 4");
        names.add("avinash - 5");
        names.add("avinash - 6");
        names.add("avinash - 7");
        names.add("avinash - 8");

        return names;
    }

    @RequestMapping("/post/add")
    @Secured("ROLE_ADMIN")
    public String addPost() {
        return "Test for add post ..... ";
    }
}

package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// this will serve thymleaf tepmplate
@Controller
public class MainCotroller {

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }
    
}

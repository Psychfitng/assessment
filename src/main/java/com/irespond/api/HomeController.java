package com.irespond.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @CrossOrigin
@RequestMapping("")
public class HomeController {

    @GetMapping("/")
    public String welcomeMessage(){
        return "Welcome home buddy";
    }
}

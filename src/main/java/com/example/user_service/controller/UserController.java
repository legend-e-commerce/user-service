package com.example.user_service.controller;

import com.example.user_service.vo.Greeting;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

    private Greeting greeting;

    public UserController(Greeting greeting) {
        this.greeting = greeting;
    }

    @GetMapping("/health_check")
    public String status() {
        return "건강해요";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    }
}

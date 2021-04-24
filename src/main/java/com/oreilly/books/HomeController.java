package com.oreilly.books;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Value("${welcomeMsg:Hello, World!}")
    private String welcomeMsg;

    @GetMapping("/")
    public String home() {
        return welcomeMsg;
    }

}

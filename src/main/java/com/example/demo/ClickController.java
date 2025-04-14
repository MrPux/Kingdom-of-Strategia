package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ClickController {

    @PostMapping("/click")
    public String handleClick(@RequestBody Map<String, String> body) {
        String action = body.get("action");
        System.out.println("Frontend sent: " + action);
        return "Got it! You " + action;
    }
}

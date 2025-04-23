package com.example.demo.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClickController, handles the '/click' endpoint to process actions sent from the frontend.
 * Over all, this is me playing around to learn about controlles in spring-boot. So ignore if you read this controller.
 */
@RestController
public class ClickController {

    /**
     * Gets executed when the frontend sends a POST request to the '/click' endpoint.
     * receives a map containing the action performed, print it to the console, and return a confirmation message.
     * @param body A map containing the action sent from the frontend.
     * @return A string confirming the action received.
     */
    @PostMapping("/click")
    public String handleClick(@RequestBody Map<String, String> body) {
        /**
         * The action performed by the user, sent from the frontend.
         */
        String action = body.get("action");
        System.out.println("Frontend sent: " + action);
        return "Got it! You " + action;
    }
}

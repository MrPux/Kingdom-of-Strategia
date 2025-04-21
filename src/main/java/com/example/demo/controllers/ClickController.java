/**
 * As the ClickController, I handle the '/click' endpoint to process actions sent from the frontend.
 */
package com.example.demo.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ClickController {

    /**
     * I am the method that gets executed when the frontend sends a POST request to the '/click' endpoint.
     * I receive a map containing the action performed, print it to the console, and return a confirmation message.
     * @param body A map containing the action sent from the frontend.
     * @return A string confirming the action received.
     */
    @PostMapping("/click")
    public String handleClick(@RequestBody Map<String, String> body) {
        String action = body.get("action");
        System.out.println("Frontend sent: " + action);
        return "Got it! You " + action;
    }
}

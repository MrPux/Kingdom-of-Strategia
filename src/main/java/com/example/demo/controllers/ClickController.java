package com.example.demo.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Rest Controller for handling click events from the frontend.
 * This controller is responsible for processing actions sent from the frontend via the '/click' endpoint.
 */
@RestController
public class ClickController {

    /**
     * Handles POST requests to the '/click' endpoint.
     * This method receives a map containing the action performed by the user, prints it to the console,
     * and returns a confirmation message to the frontend.
     *
     * @param body A map containing the action sent from the frontend. The map is expected to have a key "action"
     *             with a string value representing the action performed.
     * @return A string confirming the action received. The string includes the action performed by the user.
     */
    @PostMapping("/click")
    public String handleClick(@RequestBody Map<String, String> body) {
        // Extract the action from the request body.
        String action = body.get("action");
        // Print the action to the console for debugging purposes.
        System.out.println("Frontend sent: " + action);
        // Return a confirmation message to the frontend, including the action performed.
        return "Got it! You " + action;
    }
}

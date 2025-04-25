package com.example.demo.controllers;

import com.example.demo.classes.Village;
import com.example.demo.mechanics.generation.GameMap; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Controller for handling village-related requests.
 */
@Controller
public class VillageController {

    private final GameMap map;

    /**
     * Constructor for the VillageController.
     *
     * @param map The GameMap instance to use for retrieving village data.
     */
    public VillageController(GameMap map) {
        this.map = map;
    }

    /**
     * Displays the details of a specific village.
     *
     * @param id    The ID of the village to display.
     * @param model The Model to add the village data to.
     * @return The name of the view to render (village.html).
     */
    @GetMapping("/village/{id}")
    public String showVillage(@PathVariable int id, Model model) {
        // Retrieve the village from the GameMap based on the provided ID.
        Village village = map.getVillages().stream()
            .filter(v -> v.getId() == id) // Filter villages to find the one with the matching ID.
            .findFirst() // Get the first matching village.
            .orElse(null); // Return null if no village with the given ID is found.

        // Check if the village exists.
        if (village == null) {
            // If the village is not found, redirect to the home page.
            return "redirect:/"; // or show error
        }

        // Add the village to the model so it can be displayed in the view.
        model.addAttribute("village", village);
        // Return the name of the view to render.
        return "village";
    }
}

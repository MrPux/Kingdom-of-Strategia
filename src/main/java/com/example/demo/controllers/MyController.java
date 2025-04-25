/**
 * This controller handles requests and prepares data for the view.
 */
package com.example.demo.controllers;

import com.example.demo.mechanics.generation.GameMap;
import com.example.demo.mechanics.generation.GameMapGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for handling the main game map and related requests.
 */
@Controller
@RequestMapping("/")
public class MyController {

    private final GameMap map;

    /**
     * Constructor for the MyController.
     *
     * @param map The GameMap instance to use for game data.
     */
    public MyController(GameMap map) {
        this.map = map;
    }

    /**
     * Displays the main game map.
     * If the map is empty, it generates a new map.
     *
     * @param model The Model to add the map data to.
     * @return The name of the view to render (index.html).
     */
    @GetMapping("/hello")
    public String showMap(Model model) {
        // Check if the map is empty.
        if (map.getVillages().isEmpty()) {
            // If the map is empty, generate a new map.
            GameMap fresh = GameMapGenerator.generateMap(); // No Model param
            map.setVillages(fresh.getVillages()); // Set the villages on the map.
            map.setRoads(fresh.getRoads()); // Set the roads on the map.
            map.setMountains(fresh.getMountains()); // Set the mountains on the map.
            map.setEnemies(fresh.getEnemies()); // Set the enemies on the map.
            map.setStartingVillage(fresh.getStartingVillage()); // Set the starting village on the map.
        }

        // Add the map data to the model.
        model.addAttribute("villages", map.getVillages()); // Add the villages to the model.
        model.addAttribute("roads", map.getRoads()); // Add the roads to the model.
        model.addAttribute("mountains", map.getMountains()); // Add the mountains to the model.
        model.addAttribute("enemies", map.getEnemies()); // Add the enemies to the model.
        model.addAttribute("startingVillage", map.getStartingVillage()); // Add the starting village to the model.
        // Return the name of the view to render.
        return "index";
    }

    /**
     * Empties the game map and redirects to the main map view.
     *
     * @return Redirects to the /hello endpoint.
     */
    @PostMapping("/empty")
    public String emptyMap() {
        // Clear the map data.
        map.clearMap();
        // Redirect to the main map view.
        return "redirect:/hello";
    }
}

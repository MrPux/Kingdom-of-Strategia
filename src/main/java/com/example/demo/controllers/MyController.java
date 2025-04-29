package com.example.demo.controllers;

import com.example.demo.classes.villageClasses.Village;
import com.example.demo.mechanics.generation.GameMap;
import com.example.demo.mechanics.generation.GameMapGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.classes.ResourcesStorage;

@Controller
@RequestMapping("/")
public class MyController {

    private final GameMap map;

    public MyController(GameMap map) {
        this.map = map;
    }

    @GetMapping("/hello")
    public String showMap(Model model, @RequestParam(required = false) ResourcesStorage resources) {
        // Check if the map is empty.
        if (map.getVillages() == null || map.getVillages().isEmpty()) {
            // If the map is empty, generate a new map.
            GameMap fresh = GameMapGenerator.generateMap(); // No Model param
            map.setVillages(fresh.getVillages()); // sets the villages on the map.
            map.setRoads(fresh.getRoads()); // sets the roads on the map.
            map.setMountains(fresh.getMountains()); // sets the mountains on the map.
            map.setEnemies(fresh.getEnemies()); // sets the enemies on the map.
            map.setStartingVillage(fresh.getStartingVillage()); // sets the starting village on the map.
        }

        // Add the map data to the model.
        model.addAttribute("villages", map.getVillages()); // adds the villages to the model.
        model.addAttribute("roads", map.getRoads()); // adds the roads to the model.
        model.addAttribute("mountains", map.getMountains()); // adds the mountains to the model.
        model.addAttribute("enemies", map.getEnemies()); // adds the enemies to the model.
        model.addAttribute("startingVillage", map.getStartingVillage());

        ResourcesStorage resourcesStorage = (resources != null) ? resources : new ResourcesStorage();
        if (resources != null) {
            model.addAttribute("resourcesStorage", resources);
        } else {
            if (map.getVillages() != null) {
                for (Village village : map.getVillages()) {
                    resourcesStorage.addFromVillage(village);
                }
            }
            model.addAttribute("resourcesStorage", resourcesStorage);
        }

        // Return the name of the view to render.
        return "index";
    }

    @PostMapping("/empty")
    public String emptyMap() {
        // Clear the map data.
        map.clearMap();
        // Redirect to the main map view.
        return "redirect:/hello";
    }
}

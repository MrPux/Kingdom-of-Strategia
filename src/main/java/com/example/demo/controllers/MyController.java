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
 * This controller handles requests and prepares data for the view.
 */
@Controller
@RequestMapping("/")
public class MyController {

    private final GameMap map;

    public MyController(GameMap map) {
        this.map = map;
    }

    @GetMapping("/hello")
    public String showMap(Model model) {
        if (map.getVillages().isEmpty()) {
            GameMap fresh = GameMapGenerator.generateMap(); // No Model param
            map.setVillages(fresh.getVillages());
            map.setRoads(fresh.getRoads());
            map.setMountains(fresh.getMountains());
            map.setEnemies(fresh.getEnemies());
            map.setStartingVillage(fresh.getStartingVillage());
        }

        model.addAttribute("villages", map.getVillages());
        model.addAttribute("roads", map.getRoads());
        model.addAttribute("mountains", map.getMountains());
        model.addAttribute("enemies", map.getEnemies());
        model.addAttribute("startingVillage", map.getStartingVillage());
        return "index";
    }

    @PostMapping("/empty")
    public String emptyMap() {
        map.clearMap();
        return "redirect:/hello";
    }
}

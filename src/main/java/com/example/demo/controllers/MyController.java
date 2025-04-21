package com.example.demo.controllers;

import com.example.demo.mechanics.generation.GameMap;
import com.example.demo.mechanics.generation.MapGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping("/hello")
    public String showMap(Model model) {
        GameMap gameMap = MapGenerator.generateMap();
        model.addAttribute("villages", gameMap.getVillages());
        model.addAttribute("roads", gameMap.getRoads());
        model.addAttribute("mountains", gameMap.getMountains());
        return "index"; // Assuming this is your template name
    }
    
}

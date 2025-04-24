package com.example.demo.controllers;

import com.example.demo.classes.Village;
import com.example.demo.mechanics.generation.GameMap; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class VillageController {

    private final GameMap map;

    public VillageController(GameMap map) {
        this.map = map;
    }

    @GetMapping("/village/{id}")
    public String showVillage(@PathVariable int id, Model model) {
        Village village = map.getVillages().stream()
            .filter(v -> v.getId() == id)
            .findFirst()
            .orElse(null);

        if (village == null) {
            return "redirect:/"; // or show error
        }

        model.addAttribute("village", village);
        return "village";
    }
}


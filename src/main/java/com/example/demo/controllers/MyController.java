/**
 * As MyController, I handle requests and prepare data for the view.
 */
package com.example.demo.controllers;

import com.example.demo.mechanics.generation.GameMap;
import com.example.demo.mechanics.generation.MapGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    /**
     * I am the method that handles the '/hello' endpoint. When accessed, I generate a game map,
     * add the villages, roads, and mountains to the model, and return the 'index' view to display the map.
     * @param model The model to which I add the map data.
     * @return The name of the view to be rendered.
     */
    @GetMapping("/hello")
    public String showMap(Model model) {
        GameMap gameMap = MapGenerator.generateMap();
        model.addAttribute("villages", gameMap.getVillages());
        model.addAttribute("roads", gameMap.getRoads());
        model.addAttribute("mountains", gameMap.getMountains());
        return "index"; // Assuming this is your template name
    }
    
}

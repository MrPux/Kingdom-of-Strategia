/**
 * This controller handles requests and prepares data for the view.
 */
package com.example.demo.controllers;

import com.example.demo.mechanics.generation.GameMap;
import com.example.demo.mechanics.generation.MapGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.demo.mechanics.generation.GameMap;
import com.example.demo.mechanics.generation.MapGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This controller handles requests and prepares data for the view.
 */
@Controller
public class MyController {

    /**
     * This method handles the '/hello' endpoint. When accessed, it generates a game map,
     * adds the villages, roads, and mountains to the model, and returns the 'index' view to display the map.
     * @param model The model to which the map data is added.
     * @return The name of the view to be rendered.
     */
    @GetMapping("/hello")
    public String showMap(Model model) {
        /**
         * The generated game map.
         */
        GameMap gameMap = MapGenerator.generateMap();
        model.addAttribute("villages", gameMap.getVillages());
        model.addAttribute("roads", gameMap.getRoads());
        model.addAttribute("mountains", gameMap.getMountains());
        System.out.println("Spawned enemies: " + gameMap.getEnemies().size());
        model.addAttribute("enemies", gameMap.getEnemies());
        return "index"; // Template name
    }

}

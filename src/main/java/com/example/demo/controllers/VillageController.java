// VillageController.java
package com.example.demo.controllers;

import com.example.demo.classes.villageClasses.StructureNode;
import com.example.demo.classes.villageClasses.StructureRoad;
import com.example.demo.classes.villageClasses.Village;
import com.example.demo.mechanics.generation.GameMap;
import com.example.demo.utils.SpriteLoader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

/**
 * Controller for handling village-related requests.
 */
@Controller
public class VillageController {

    private final GameMap map;

    /**
     * Constructs a VillageController with the given game map.
     *
     * @param map The game map.
     */
    public VillageController(GameMap map) {
        this.map = map;
    }

    /**
     * Displays the village with the given ID.
     *
     * @param id    The ID of the village to display.
     * @param model The model to add attributes to.
     * @return The name of the view to render.
     */
    @GetMapping("/village/{id}")
    public String showVillage(@PathVariable int id, Model model) {
        Village village = map.getVillages().stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElse(null);

        if (village == null) {
            return "redirect:/";
        }

        model.addAttribute("village", village);
        model.addAttribute("hasNegativeCycle", village.hasNegativeCycle());

        try {
            String graphData = generateGraphData(village);
            model.addAttribute("graphData", graphData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            model.addAttribute("graphData", "[]");
        }

        String fountainSprite = SpriteLoader.getFountainSprite(village.getType());
        model.addAttribute("fountainSprite", fountainSprite);

        return "village";
    }

    /**
     * Generates the graph data for the village.
     *
     * @param village The village to generate graph data for.
     * @return The graph data as a JSON string.
     * @throws JsonProcessingException If there is an error processing the JSON.
     */
    private String generateGraphData(Village village) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Object> nodes = new ArrayList<>();
        List<Object> edges = new ArrayList<>();
    
        List<StructureNode> structureNodes = village.getStructuresList();
    
        // Track connected node IDs
        Map<Integer, Boolean> isConnected = new HashMap<>();
    
        for (StructureNode node : structureNodes) {
            Map<String, Object> nodeData = new HashMap<>();
            Map<String, Object> data = new HashMap<>();
            Random rand = new Random(node.getId()); // ✅ seed the randomness per node so it's stable
            data.put("id", node.getId());
            data.put("houseSprite", node.getSprite());
            data.put("x", rand.nextInt(600) + 100); // x between 100-700
            data.put("y", rand.nextInt(400) + 100); // y between 100-500            
            nodeData.put("data", data);
            nodes.add(nodeData);
    
            boolean hasEdge = false;
    
            for (StructureRoad edge : node.getConnections()) {
                hasEdge = true;
                StructureNode to = edge.getToStructure();
    
                Map<String, Object> edgeData = new HashMap<>();
                Map<String, Object> edgeProps = new HashMap<>();
                edgeProps.put("id", node.getId() + "-" + to.getId());
                edgeProps.put("source", node.getId());
                edgeProps.put("target", to.getId());
                edgeProps.put("weight", edge.getWeight());
                edgeData.put("data", edgeProps);
                edges.add(edgeData);
    
                isConnected.put(node.getId(), true);
                isConnected.put(to.getId(), true);
            }
    
            if (!hasEdge) {
                isConnected.putIfAbsent(node.getId(), false); // Mark as unconnected if no edges from it
            }
        }
    
        // 🔧 Fix disconnected nodes
        for (StructureNode node : structureNodes) {
            if (!isConnected.getOrDefault(node.getId(), false)) {
                // Pick nearest connected node (just pick the first one with a connection)
                for (StructureNode target : structureNodes) {
                    if (!node.equals(target) && isConnected.getOrDefault(target.getId(), true)) {
                        Map<String, Object> edgeData = new HashMap<>();
                        Map<String, Object> edgeProps = new HashMap<>();
                        edgeProps.put("id", node.getId() + "-fix-" + target.getId());
                        edgeProps.put("source", node.getId());
                        edgeProps.put("target", target.getId());
                        edgeProps.put("weight", 0); // neutral edge
                        edgeData.put("data", edgeProps);
                        edges.add(edgeData);
                        System.out.println("🔧 Connected orphan node " + node.getId() + " → " + target.getId());
                        break;
                    }
                }
            }
        }
    
        // Add fountain node if needed
        String fountainSprite = SpriteLoader.getFountainSprite(village.getType());
        Map<String, Object> fountainNode = new HashMap<>();
        Map<String, Object> fountainData = new HashMap<>();
        fountainData.put("id", "fountain");
        fountainData.put("houseSprite", fountainSprite);
        fountainNode.put("data", fountainData);
        nodes.add(fountainNode);
    
        Map<String, Object> graphData = new HashMap<>();
        graphData.put("nodes", nodes);
        graphData.put("edges", edges);
    
        return objectMapper.writeValueAsString(graphData);
    }
    
}

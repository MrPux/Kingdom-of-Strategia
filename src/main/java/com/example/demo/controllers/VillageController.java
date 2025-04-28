// VillageController.java
package com.example.demo.controllers;

import com.example.demo.classes.villageClasses.StructureNode;
import com.example.demo.classes.villageClasses.StructureRoad;
import com.example.demo.classes.villageClasses.Village;
import com.example.demo.mechanics.generation.GameMap;
import com.example.demo.mechanics.pathfinding.Dijkstra;
import com.example.demo.utils.SpriteLoader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

/**
 * <h1>VillageController</h1>
 * <p>
 * Controller for handling village-related requests.
 * </p>
 * <p>
 * This controller manages the display of village information, including structure layouts,
 * resource details, and pathfinding visualizations using Dijkstra's algorithm.
 * </p>
 */
@Controller
public class VillageController {

    private final GameMap map;

    /**
     * <h1>VillageController Constructor</h1>
     * <p>
     * Constructs a VillageController with the given game map.
     * </p>
     *
     * @param map The game map.
     */
    public VillageController(GameMap map) {
        this.map = map;
    }

    /**
     * <h1>showVillage Method</h1>
     * <p>
     * Displays the village with the given ID.
     * </p>
     * <p>
     * This method retrieves the village from the game map, adds village attributes to the model,
     * generates graph data for visualization, and integrates Dijkstra's algorithm to provide
     * pathfinding animation steps.
     * </p>
     *
     * @param id    The ID of the village to display.
     * @param model The model to add attributes to.
     * @return The name of the view to render ("village").
     */
    @GetMapping("/village/{id}")
    public String showVillage(@PathVariable int id, Model model) {
        // Retrieve the village from the game map based on the provided ID.
        Village village = map.getVillages().stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElse(null);

        // If the village is not found, redirect to the home page.
        if (village == null) {
            return "redirect:/";
        }

        // Check for negative edges in the village structure.
        boolean hasNegativeEdge = false;

        for (StructureNode node : village.getStructuresList()) {
            for (StructureRoad road : node.getConnections()) {
                System.out.println("Checking road: " + road.getFromStructure().getId() + " -> " + road.getToStructure().getId() + " weight: " + road.getWeight());
                if (road.getWeight() < 0) {
                    hasNegativeEdge = true;
                    break;
                }
            }
            if (hasNegativeEdge) break;
        }

        // Add attributes to the model for rendering in the view.
        model.addAttribute("hasNegativeEdge", hasNegativeEdge);
        model.addAttribute("village", village);
        model.addAttribute("hasNegativeCycle", village.hasNegativeCycle());

        // Generate graph data for the village.
        try {
            String graphData = generateGraphData(village);
            model.addAttribute("graphData", graphData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            model.addAttribute("graphData", "[]");
        }

        // Get the fountain sprite for the village type.
        String fountainSprite = SpriteLoader.getFountainSprite(village.getType());
        model.addAttribute("fountainSprite", fountainSprite);

        // 🛠️ Dijkstra steps generation:
        try {
            String startId = null;

            for (StructureNode node : village.getStructuresList()) {
                if (!node.getSprite().contains("Fountain")) { // 🚿 skip fountain
                    for (StructureRoad road : node.getConnections()) {
                        if (road.getFromStructure().getId() == node.getId() || road.getToStructure().getId() == node.getId()) {
                            startId = String.valueOf(node.getId());
                            System.out.println("🧭 Found valid starting node: " + startId);
                            break;
                        }
                    }
                }
                if (startId != null) break;
            }

            if (startId == null) {
                System.out.println("⚠️ No valid start node found for Dijkstra!");
                startId = "0"; // fallback
            }


            // 🔥 Correct way: get edges from the nodes themselves
            List<StructureRoad> structureEdges = new ArrayList<>();
            for (StructureNode node : village.getStructuresList()) {
                structureEdges.addAll(node.getConnections());
            }

            // Generate Dijkstra animation steps.
            List<Dijkstra.AnimationStep> steps = Dijkstra.generateDijkstraAnimation(
                    village.getStructuresList(),
                    structureEdges, // ✅ Correct list
                    startId
            );

            // Convert animation steps to JSON format.
            ObjectMapper mapper = new ObjectMapper();
            String animationStepsJson = mapper.writeValueAsString(steps);
            model.addAttribute("animationSteps", animationStepsJson);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("animationSteps", "[]");
        }




        return "village";
    }

    /**
     * <h1>generateGraphData Method</h1>
     * <p>
     * Generates the graph data for the village.
     * </p>
     * <p>
     * This method creates a JSON representation of the village's structure graph, including nodes
     * and edges, for use in visualization libraries.
     * </p>
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

            // Add outgoing edges
            for (StructureRoad edge : node.getConnections()) {
                hasEdge = true;
                StructureNode to = edge.getToStructure();
                addEdge(nodes, edges, node, to, edge.getWeight(), isConnected);
            }

            // Add incoming edges
            for (StructureRoad edge : village.getStructureRoads()) {
                if (edge.getToStructure().equals(node)) {
                    hasEdge = true;
                    StructureNode from = edge.getFromStructure();
                    addEdge(nodes, edges, from, node, edge.getWeight(), isConnected);
                }
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

    /**
     * <h1>addEdge Method</h1>
     * <p>
     * Adds an edge to the graph data.
     * </p>
     * <p>
     * This method creates an edge between two structure nodes and adds it to the list of edges.
     * It also updates the isConnected map to track connected nodes.
     * </p>
     *
     * @param nodes       The list of nodes in the graph.
     * @param edges       The list of edges in the graph.
     * @param from        The source structure node.
     * @param to          The target structure node.
     * @param weight      The weight of the edge.
     * @param isConnected A map to track connected node IDs.
     */
    private void addEdge(List<Object> nodes, List<Object> edges, StructureNode from, StructureNode to, int weight, Map<Integer, Boolean> isConnected) {
        Map<String, Object> edgeData = new HashMap<>();
        Map<String, Object> edgeProps = new HashMap<>();
        edgeProps.put("id", from.getId() + "-" + to.getId());
        edgeProps.put("source", from.getId());
        edgeProps.put("target", to.getId());
        edgeProps.put("weight", weight);
        edgeData.put("data", edgeProps);
        edges.add(edgeData);

        isConnected.put(from.getId(), true);
        isConnected.put(to.getId(), true);
    }
}

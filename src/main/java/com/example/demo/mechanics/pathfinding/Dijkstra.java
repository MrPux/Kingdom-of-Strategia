package com.example.demo.mechanics.pathfinding;

import com.example.demo.classes.villageClasses.StructureNode;
import com.example.demo.classes.villageClasses.StructureRoad;

import java.util.*;

public class Dijkstra {

    /**
     * <h2>AnimationStep Class</h2>
     * Represents a single step in the Dijkstra's algorithm animation.
     * It records the action performed ("visit" or "finalize"), the starting node ("from"),
     * and the destination node ("to").
     */
    public static class AnimationStep {
        private String action; // "visit" or "finalize"
        private String from;
        private String to;
        private int weight;

        /**
         * Constructs an AnimationStep object.
         * @param action The action performed in this step ("visit" or "finalize").
         * @param from The ID of the starting node.
         * @param to The ID of the destination node.
         */
        public AnimationStep(String action, String from, String to, int weight) {
            this.action = action;
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        /**
         * Gets the action performed in this animation step.
         * @return The action ("visit" or "finalize").
         */
        public String getAction() {
            return action;
        }

        /**
         * Gets the ID of the starting node for this animation step.
         * @return The ID of the starting node.
         */
        public String getFrom() {
            return from;
        }

        /**
         * Gets the ID of the destination node for this animation step.
         * @return The ID of the destination node.
         */
        public String getTo() {
            return to;
        }
    }

    /**
     * <h2>generateDijkstraAnimation Method</h2>
     * Generates a list of animation steps that visualize Dijkstra's algorithm.
     *
     * @param nodes A list of StructureNode objects representing the nodes in the graph.
     * @param edges A list of StructureRoad objects representing the edges in the graph.
     *              Each edge contains information about the 'from' and 'to' nodes, as well as the weight (distance)
     *              between them.
     * @param startId The ID of the starting node for Dijkstra's algorithm.
     * @return A list of AnimationStep objects representing the animation of Dijkstra's algorithm.
     */
    public static List<AnimationStep> generateDijkstraAnimation(List<StructureNode> nodes, List<StructureRoad> edges, String startId) {
        // distances: Stores the shortest distance from the start node to each node in the graph.
        Map<String, Integer> distances = new HashMap<>();
        // previous: Stores the previous node in the shortest path from the start node to each node.
        Map<String, String> previous = new HashMap<>();
        // visited: Keeps track of the nodes that have already been visited.
        Set<String> visited = new HashSet<>();
        // animationSteps: Stores the steps of the algorithm for visualization.
        List<AnimationStep> animationSteps = new ArrayList<>();

        // Initialize all distances to "infinite" (Integer.MAX_VALUE)
        // This means initially, we assume that all nodes are unreachable from the start node.
        for (StructureNode node : nodes) {
            distances.put(String.valueOf(node.getId()), Integer.MAX_VALUE);
        }
        // sets the distance from the start node to itself to 0.
        distances.put(startId, 0);

        // The main loop of Dijkstra's algorithm.
        // It continues until all nodes have been visited (or are unreachable).
        while (visited.size() < nodes.size()) {
            // Find the node with the smallest distance that has not yet been visited.
            String current = null;
            int minDistance = Integer.MAX_VALUE;
            for (String nodeId : distances.keySet()) {
                // Check if the node has not been visited and if its distance is less than the current minimum distance.
                if (!visited.contains(nodeId) && distances.get(nodeId) < minDistance) {
                    // If both conditions are met, update the minimum distance and the current node.
                    minDistance = distances.get(nodeId);
                    current = nodeId;
                }
            }

            // If no reachable node is left, exit the loop.
            if (current == null) break; // no reachable node left
            // Mark the current node as visited.
            visited.add(current);

            // Iterate over all the edges in the graph.
            for (StructureRoad road : edges) {
                // Get the 'from' and 'to' nodes of the current edge.
                String from = String.valueOf(road.getFromStructure().getId());
                String to = String.valueOf(road.getToStructure().getId());
                // Get the weight (distance) of the current edge.
                int weight = road.getWeight();

                // Determine the neighbor node (the node that is connected to the current node by the current edge).
                String neighbor = null;
                if (from.equals(current)) neighbor = to;
                else if (to.equals(current)) neighbor = from;
                else continue; // If the current edge does not connect to the current node, skip to the next edge.

                // If the neighbor node has already been visited, skip to the next edge.
                if (visited.contains(neighbor)) continue;
                // Add an animation step to indicate that the algorithm is visiting the neighbor node.
                animationSteps.add(new AnimationStep("visit", current, neighbor, weight));

                // Calculate the new distance from the start node to the neighbor node.
                int newDistance = distances.get(current) + weight;
                // If the new distance is less than the current distance to the neighbor node, update the distance and the previous node.
                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    previous.put(neighbor, current);
                }
            }
        }

        // After all nodes visited, highlight shortest paths
        // This loop iterates over the 'previous' map, which contains the previous node in the shortest path from the start node to each node.
        for (Map.Entry<String, String> entry : previous.entrySet()) {
            if (visited.contains(entry.getKey()) && visited.contains(entry.getValue())) {
                int from = Integer.parseInt(entry.getValue());
                int to = Integer.parseInt(entry.getKey());
                int weight = findEdgeWeight(edges, from, to);
        
                animationSteps.add(new AnimationStep("finalize", entry.getValue(), entry.getKey(), weight));
            }
        }

        return animationSteps;
    }
    private static int findEdgeWeight(List<StructureRoad> edges, int from, int to) {
        for (StructureRoad edge : edges) {
            if ((edge.getFromStructure().getId() == from && edge.getToStructure().getId() == to) ||
                (edge.getFromStructure().getId() == to && edge.getToStructure().getId() == from)) {
                return edge.getWeight();
            }
        }
        return 0; // default, should not happen
    }
    
}

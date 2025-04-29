package com.example.demo.mechanics.pathfinding;

import com.example.demo.classes.villageClasses.StructureNode;
import com.example.demo.classes.villageClasses.StructureRoad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h1>BellmanFord</h1>
 * <p>
 * This class implements the Bellman-Ford algorithm to detect negative cycles in a graph.
 * </p>
 * <p>
 * The BellmanFord class is used to determine if a given graph contains a negative cycle.
 * A negative cycle is a cycle in the graph where the sum of the edge weights is negative,
 * which can cause issues with pathfinding algorithms.
 * </p>
 */
public class BellmanFord {

    /**
     * <h1>hasNegativeCycle Method</h1>
     * <p>
     * Checks if a given graph (list of structure nodes) contains a negative cycle.
     * </p>
     * <p>
     * This method implements the Bellman-Ford algorithm to detect negative cycles in a graph.
     * It initializes distances to all nodes as infinity, and then relaxes all edges V-1 times,
     * where V is the number of vertices in the graph. After this, it checks if any edge can be
     * further relaxed, which indicates the presence of a negative cycle.
     * </p>
     *
     * @param graph The list of structure nodes representing the graph.
     * @return True if the graph contains a negative cycle, false otherwise.
     */
    public static boolean hasNegativeCycle(List<StructureNode> graph) {
        int nodeCount = graph.size();
        int[] distances = new int[nodeCount];

        // Initialize distances with a large value (infinity)
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[0] = 0; // Start with the first node, distance is 0

        // Relax all edges V-1 times
        for (int i = 1; i < nodeCount; i++) {
            for (StructureNode node : graph) {
                for (StructureRoad edge : node.getConnections()) {
                    int u = node.getId(); // ✅ Corrected
                    int v = edge.getToStructure().getId(); // ✅ Corrected
                    int weight = edge.getWeight();

                    if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                        distances[v] = distances[u] + weight;
                    }
                }
            }
        }

        // Check for negative weight cycles
        for (StructureNode node : graph) {
            for (StructureRoad edge : node.getConnections()) {
                int u = node.getId(); // ✅ Corrected
                int v = edge.getToStructure().getId(); // ✅ Corrected
                int weight = edge.getWeight();

                if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                    return true;  // Negative cycle detected
                }
            }
        }

        return false;  // No negative cycle found
    }





    public static class AnimationStep {
        private String action; // "relax" or "finalize"
        private String from;
        private String to;

        public AnimationStep(String action, String from, String to) {
            this.action = action;
            this.from = from;
            this.to = to;
        }

        public String getAction() {
            return action;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }
    }



    /** Runs Bellman-Ford and generates animation steps.
     *
     * @param nodes List of structure nodes
     * @param edges List of structure roads (edges)
     * @param startId Starting node ID as string
     * @return List of animation steps
     */
    public static List<AnimationStep> generateBellmanFordAnimation(List<StructureNode> nodes, List<StructureRoad> edges, String startId) {
        int nodeCount = nodes.size();
        int[] distances = new int[nodeCount];
        String[] previous = new String[nodeCount];
        List<AnimationStep> animationSteps = new ArrayList<>();
    
        // Step 1.1: Initialize all distances to "infinite"
        Arrays.fill(distances, Integer.MAX_VALUE);
    
        // Step 1.2: Set the starting node distance to 0
        distances[Integer.parseInt(startId)] = 0;

        // Step 2: Relax all edges (V-1) times
        for(int i = 1; i < nodeCount; i++)
        {
            for(StructureRoad edge : edges)
            {
                int u = edge.getFromStructure().getId();
                int v = edge.getToStructure().getId();
                int weight = edge.getWeight();

                if(distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v])
                {
                    distances[v] = distances[u] + weight;
                    previous[v] = String.valueOf(u);

                    // 👇 Animation step showing "visit" (updating path)Y
                    animationSteps.add(new AnimationStep("visit", String.valueOf(u), String.valueOf(v)));

                }
            }
        }

        // Step 3: Finalize the shortest paths
        for(int v = 0; v < nodeCount; v++)
        {
            if(previous[v] != null)
            {   
                animationSteps.add(new AnimationStep("finalize", previous[v], String.valueOf(v)));
            }
        }
    
        return animationSteps; // temporary, so no red error yet
    }
    
}

package com.example.demo.mechanics.pathfinding;

import com.example.demo.classes.villageClasses.StructureNode;
import com.example.demo.classes.villageClasses.StructureRoad;

import java.util.*;

/**
 * <h1>Floyd-Warshall Pathfinding Animation</h1>
 * <p>Generates animation steps for Floyd-Warshall algorithm.</p>
 */
public class FloydWarshall {

    /**
     * AnimationStep class for Floyd-Warshall.
     */
    public static class AnimationStep {
        private String action; // "visit" or "finalize"
        private String from;
        private String to;
        private int weight;

        public AnimationStep(String action, String from, String to, int weight) {
            this.action = action;
            this.from = from;
            this.to = to;
            this.weight = weight;
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

        public int getWeight() {
            return weight;
        }
    }

    public static List<AnimationStep> generateFloydWarshallAnimation(List<StructureNode> nodes, List<StructureRoad> edges) {
        int n = nodes.size();
        int[][] dist = new int[n][n];
        int[][] next = new int[n][n];

        // Initialize distance and next matrices
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            Arrays.fill(next[i], -1);
            dist[i][i] = 0;
        }

        for (StructureRoad edge : edges) {
            int u = edge.getFromStructure().getId();
            int v = edge.getToStructure().getId();
            dist[u][v] = edge.getWeight();
            next[u][v] = v;

            // If undirected, also set reverse
            dist[v][u] = edge.getWeight();
            next[v][u] = u;
        }

        List<AnimationStep> animationSteps = new ArrayList<>();

        // Floyd-Warshall main loop
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE
                            && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                        animationSteps.add(new AnimationStep("visit", String.valueOf(i), String.valueOf(j), dist[i][j]));
                    }
                }
            }
        }

        // Reconstruct only actual used edges
        Set<String> finalizedPairs = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && next[i][j] != -1 && dist[i][j] != Integer.MAX_VALUE) {
                    int u = i;
                    while (u != j) {
                        int v = next[u][j];
                        if (v == -1) break;

                        String key1 = u + "-" + v;
                        String key2 = v + "-" + u;

                        if (!finalizedPairs.contains(key1) && !finalizedPairs.contains(key2)) {
                            animationSteps.add(new AnimationStep("finalize", String.valueOf(u), String.valueOf(v), dist[u][v]));
                            finalizedPairs.add(key1);
                            finalizedPairs.add(key2);
                        }

                        u = v;
                    }
                }
            }
        }

        return animationSteps;
    }
}

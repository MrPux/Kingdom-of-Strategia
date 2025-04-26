/**
 * This class builds the Minimum Spanning Tree (MST) for pathfinding.
 */
package com.example.demo.mechanics.pathfinding;

import java.util.*;

import com.example.demo.classes.Road;
import com.example.demo.classes.villageClasses.Village;

/**
 * This class builds the Minimum Spanning Tree (MST) for pathfinding.
 * It uses Kruskal's algorithm with a Disjoint Set data structure to find the MST.
 */
public class MSTBuilder {

    /**
     * Builds the Minimum Spanning Tree (MST) from a list of villages and all possible roads.
     *
     * @param villages The list of villages in the game.
     * @param allRoads The list of all possible roads between villages.
     * @return A list of roads that form the MST.
     */
    public static List<Road> buildMST(List<Village> villages, List<Road> allRoads) {
        // Sort all roads by weight in ascending order
        allRoads.sort(Comparator.comparingInt(Road::getWeight));

        // Disjoint Set (Union-Find) data structure to track connected components
        Map<Village, Village> parent = new HashMap<>();
        for (Village v : villages) {
            parent.put(v, v); // Each village is initially its own parent
        }

        List<Road> mst = new ArrayList<>(); // List to store the roads in the MST

        // Iterate through all roads in sorted order
        for (Road road : allRoads) {
            Village v1 = road.getFromVillage();
            Village v2 = road.getToVillage();

            // If the villages are not in the same connected component
            if (find(parent, v1) != find(parent, v2)) {
                mst.add(road); // Add the road to the MST
                union(parent, v1, v2); // Union the two connected components
            }
        }

        return mst;
    }

    /**
     * Finds the root of the connected component that a village belongs to (path compression).
     *
     * @param parent The map representing the parent of each village in the Disjoint Set.
     * @param v      The village to find the root for.
     * @return The root village of the connected component.
     */
    private static Village find(Map<Village, Village> parent, Village v) {
        // Path compression: set the parent of v to the root of its connected component
        if (parent.get(v) != v) {
            parent.put(v, find(parent, parent.get(v)));
        }
        return parent.get(v);
    }

    /**
     * Unions the connected components of two villages.
     *
     * @param parent The map representing the parent of each village in the Disjoint Set.
     * @param v1     The first village.
     * @param v2     The second village.
     */
    private static void union(Map<Village, Village> parent, Village v1, Village v2) {
        Village root1 = find(parent, v1); // Find the root of the first village
        Village root2 = find(parent, v2); // Find the root of the second village
        parent.put(root1, root2); // Set the parent of the first root to the second root
    }
}

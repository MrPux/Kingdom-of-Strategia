/**
 * This class builds the Minimum Spanning Tree (MST) for pathfinding.
 */
package com.example.demo.mechanics.pathfinding;

import java.util.*;

import com.example.demo.classes.Road;
import com.example.demo.classes.villageClasses.Village;

/**
 * <h1>MSTBuilder</h1>
 * <p>
 * This class builds the Minimum Spanning Tree (MST) for pathfinding.
 * It uses Kruskal's algorithm with a Disjoint Set data structure to find the MST.
 * </p>
 * <p>
 * The MSTBuilder class is responsible for constructing a Minimum Spanning Tree (MST) from a given
 * set of villages and roads. The MST is a subset of the roads that connects all the villages
 * together with the minimum possible total road weight. This is useful for pathfinding and
 * network optimization in the game.
 * </p>
 */
public class MSTBuilder {

    /**
     * <h1>buildMST Method</h1>
     * <p>
     * Builds the Minimum Spanning Tree (MST) from a list of villages and all possible roads.
     * </p>
     * <p>
     * This method implements Kruskal's algorithm to find the MST. It sorts all the roads by weight,
     * and then iterates through the sorted roads, adding each road to the MST if it connects two
     * villages that are not already connected in the MST. A Disjoint Set data structure is used to
     * track the connected components of the villages.
     * </p>
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
     * <h1>find Method</h1>
     * <p>
     * Finds the root of the connected component that a village belongs to (path compression).
     * </p>
     * <p>
     * This method recursively finds the root of the connected component that a village belongs to.
     * It also performs path compression, which optimizes the Disjoint Set data structure by setting
     * the parent of each village on the path to the root to be the root itself.
     * </p>
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
     * <h1>union Method</h1>
     * <p>
     * Unions the connected components of two villages.
     * </p>
     * <p>
     * This method merges the connected components of two villages by setting the parent of the root
     * of the first village's connected component to the root of the second village's connected component.
     * </p>
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

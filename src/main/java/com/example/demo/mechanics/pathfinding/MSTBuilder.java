package com.example.demo.mechanics.pathfinding;

import java.util.*;

import com.example.demo.classes.Road;
import com.example.demo.classes.villageClasses.Village;

public class MSTBuilder {

    /**
     * ## buildMST
     *
     * Builds the Minimum Spanning Tree (MST) from a list of villages and all possible roads using Kruskal's algorithm.
     * The algorithm sorts all possible roads by weight and iteratively adds the road with the smallest weight
     * that doesn't create a cycle, until all villages are connected.
     *
     * @param villages The list of villages in the game.
     * @param allRoads The list of all possible roads between villages.
     * @return A list of roads that form the MST.
     */
    public static List<Road> buildMST(List<Village> villages, List<Road> allRoads) {
        allRoads.sort(Comparator.comparingInt(Road::getWeight));

        Map<Village, Village> parent = new HashMap<>();
        for (Village v : villages) {
            parent.put(v, v);
        }

        List<Road> mst = new ArrayList<>();

        for (Road road : allRoads) {
            Village v1 = road.getFromVillage();
            Village v2 = road.getToVillage();

            if (find(parent, v1) != find(parent, v2)) {
                mst.add(road);
                union(parent, v1, v2);
            }
        }

        return mst;
    }

    /**
     * ## find
     *
     * Finds the root of the connected component that a village belongs to, implementing path compression.
     * Path compression optimizes future find operations by making each node on the path to the root point directly to the root.
     *
     * @param parent The map representing the parent of each village in the Disjoint Set.
     * @param v      The village to find the root for.
     * @return The root village of the connected component.
     */
    private static Village find(Map<Village, Village> parent, Village v) {
        if (parent.get(v) != v) {
            parent.put(v, find(parent, parent.get(v)));
        }
        return parent.get(v);
    }

    /**
     * ## union
     *
     * Unions the connected components of two villages by setting the parent of one root to the other.
     * This operation merges the two sets, indicating that the villages are now part of the same connected component.
     *
     * @param parent The map representing the parent of each village in the Disjoint Set.
     * @param v1     The first village.
     * @param v2     The second village.
     */
    private static void union(Map<Village, Village> parent, Village v1, Village v2) {
        Village root1 = find(parent, v1);
        Village root2 = find(parent, v2);
        parent.put(root1, root2);
    }
}

/**
 * This class builds the Minimum Spanning Tree (MST) for pathfinding.
 */
package com.example.demo.mechanics.pathfinding;

import java.util.*;

import com.example.demo.classes.Road;
import com.example.demo.classes.Village;

public class MSTBuilder {

    public static List<Road> buildMST(List<Village> villages, List<Road> allRoads) {
        // Sort all roads by weight
        allRoads.sort(Comparator.comparingInt(Road::getWeight));

        // Disjoint Set (Union-Find)
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

    private static Village find(Map<Village, Village> parent, Village v) {
        if (parent.get(v) != v) {
            parent.put(v, find(parent, parent.get(v)));
        }
        return parent.get(v);
    }

    private static void union(Map<Village, Village> parent, Village v1, Village v2) {
        Village root1 = find(parent, v1);
        Village root2 = find(parent, v2);
        parent.put(root1, root2);
    }

    
}

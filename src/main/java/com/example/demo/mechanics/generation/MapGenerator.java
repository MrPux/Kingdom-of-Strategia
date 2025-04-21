package com.example.demo.mechanics.generation;

import com.example.demo.core.Mountain;
import com.example.demo.core.Road;
import com.example.demo.core.Village;
import com.example.demo.mechanics.pathfinding.MSTBuilder;
import com.example.demo.utils.GameUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapGenerator {
    private static final int MAX_VILLAGES = 10;
    private static final int MAX_MOUNTAINS = 20;
    private static final int MAX_X = 900 - 80; //My sidebar is 300px
    private static final int MAX_Y = 900 - 80;

    public static GameMap generateMap() {
        GameMap map = new GameMap();
        Random rand = new Random();

        List<Road> allRoads = new ArrayList<>();

        // Create villages
        for (int i = 0; i < MAX_VILLAGES; i++) {
            int x = rand.nextInt(MAX_X);
            int y = rand.nextInt(MAX_Y);
            Village village = new Village("Village" + i, i, x, y);
            map.addVillage(village);
        }

        //Create mountains
        for(int i = 0; i < MAX_MOUNTAINS; i++)
        {
            int x = rand.nextInt(MAX_X);
            int y = rand.nextInt(MAX_Y);
            map.addMountain(new Mountain(x, y));
        }

        // Create all possible roads between pairs
        List<Village> villages = map.getVillages();
        for (int i = 0; i < villages.size(); i++) {
            for (int j = i + 1; j < villages.size(); j++) {
                Village v1 = villages.get(i);
                Village v2 = villages.get(j);
                int weight = GameUtils.calculateDistance(v1, v2);
                allRoads.add(new Road(v1, v2, weight));
            }
        }

        // Build MST roads
        List<Road> mstRoads = MSTBuilder.buildMST(villages, allRoads);
        for (Road road : mstRoads) {
            map.addRoad(road);
        }

        return map;
    }
}


/**
 * This class generates the game map.
 */
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
    private static final int MAX_VILLAGES = 40;
    private static final int MIN_DISTANCE = 60; // grid size, or increase if needed

    private static final int MAX_MOUNTAINS = 20;
    private static final int MOUNTAIN_RADIUS = 34;
    private static final int MAX_X = 900 - 80; //My sidebar is 300px
    private static final int MAX_Y = 900 - 80;

    public static GameMap generateMap() {
        GameMap map = new GameMap();
        Random rand = new Random();

        List<Road> allRoads = new ArrayList<>();

        

        // Create villages
        for (int i = 0; i < MAX_VILLAGES; i++) {
            int x, y;
            boolean validPosition;
        
            do {
                x = rand.nextInt(MAX_X / 50) * 50 + 300;
                y = rand.nextInt(MAX_Y / 50) * 50 + 10;

                validPosition = true;
        
                for (Village existing : map.getVillages()) {
                    double dx = existing.getXCoordinate() - x;
                    double dy = existing.getYCoordinate() - y;
                    double distance = Math.sqrt(dx * dx + dy * dy);
        
                    if (distance < MIN_DISTANCE) {
                        validPosition = false;
                        break;
                    }
                }
        
            } while (!validPosition);
        
            Village village = new Village("Village" + i, i, x, y);
            map.addVillage(village);
        }
        

        //Create mountains
        for (int i = 0; i < MAX_MOUNTAINS; i++) {
            int x, y;
            boolean validPosition;
        
            do {
                x = rand.nextInt(MAX_X / 50) * 50 + 300;
                y = rand.nextInt(MAX_Y / 50) * 50 + 10;
                validPosition = true;
        
                // Avoid overlap with existing mountains
                for (Mountain existing : map.getMountains()) {
                    double dx = existing.getXCoordinate() - x;
                    double dy = existing.getYCoordinate() - y;
                    double distance = Math.sqrt(dx * dx + dy * dy);
        
                    if (distance < MIN_DISTANCE) {
                        validPosition = false;
                        break;
                    }
                }
        
                // Also avoid overlap with villages
                for (Village existingVillage : map.getVillages()) {
                    double dx = existingVillage.getXCoordinate() - x;
                    double dy = existingVillage.getYCoordinate() - y;
                    double distance = Math.sqrt(dx * dx + dy * dy);
        
                    if (distance < MIN_DISTANCE) {
                        validPosition = false;
                        break;
                    }
                }
        
            } while (!validPosition);
        
            map.addMountain(new Mountain(x, y));
        }
        
        

        // Create all possible roads between pairs
        List<Village> villages = map.getVillages();
        for (int i = 0; i < villages.size(); i++) {
            for (int j = i + 1; j < villages.size(); j++) {
                Village v1 = villages.get(i);
                Village v2 = villages.get(j);
        
                boolean intersectsMountain = false;
                for (Mountain mountain : map.getMountains()) {
                    if (GameUtils.lineIntersectsCircle(
                            v1.getXCoordinate(), v1.getYCoordinate(),
                            v2.getXCoordinate(), v2.getYCoordinate(),
                            mountain.getXCoordinate(), mountain.getYCoordinate(),
                            MOUNTAIN_RADIUS
                            )) // Adjust if your mountain image is wider
                    {
                        intersectsMountain = true;
                        break;
                    }
                }
         
                if (!intersectsMountain) {
                    int weight = GameUtils.calculateDistance(v1, v2);
                    allRoads.add(new Road(v1, v2, weight));
                }
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

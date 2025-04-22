
/**
 * This class generates the game map.
 */
package com.example.demo.mechanics.generation;

import com.example.demo.classes.Enemy;
import com.example.demo.classes.Mountain;
import com.example.demo.classes.Road;
import com.example.demo.classes.Village;
import com.example.demo.mechanics.pathfinding.MSTBuilder;
import com.example.demo.utils.GameUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapGenerator {
    // assuming your village sprite is 60×60, mountain is 80×80
    private static final int VILLAGE_HALF = 30;
    private static final int MOUNTAIN_HALF = 40;

    // Maximum number of mountains to generate.
    private static final int MAX_MOUNTAINS = 20;
    // Radius of the mountains, to avoid road collision purposes.
    private static final int MOUNTAIN_RADIUS = 34;

    // Maximum number of villages to generate.
    private static final int MAX_VILLAGES = 40;
    // Minimum distance between villages, to avoid villages to stack.
    private static final int MIN_DISTANCE = 100; // grid size, or increase if needed.

    // Maximum X coordinate for village generation.
    private static final int MAX_X = 900 - 80; //My sidebar is 300px.
    // Maximum Y coordinate for village generation.
    private static final int MAX_Y = 900 - 80;

    // Maximum percantege of enemy spawning.@interface
    private static double enemyChance = 0.3;
    

    public static GameMap generateMap() {
        // Create a new game map
        GameMap map = new GameMap();
        // Create a new random number generator
        Random rand = new Random();

        // Create a list to hold all possible roads
        List<Road> allRoads = new ArrayList<>();
        //Create mountains
        for (int i = 0; i < MAX_MOUNTAINS; i++) {
            // X and Y coordinates for the mountain
            int x, y;
            // Flag to check if the position is valid
            boolean validPosition;

            do {
                // Generate random X and Y coordinates
                x = rand.nextInt(MAX_X / 50) * 50 + 300;
                y = rand.nextInt(MAX_Y / 50) * 50 + 20;
                validPosition = true;

                // Avoid overlap with existing mountains
                for (Mountain existing : map.getMountains()) {
                    // Calculate the difference in x-coordinates
                    double dx = existing.getXCoordinate() - x;
                    // Calculate the difference in y-coordinates
                    double dy = existing.getYCoordinate() - y;
                    // Calculate the distance between the two mountains
                    double distance = Math.sqrt(dx * dx + dy * dy);

                    // If the distance is less than the minimum distance, the position is not valid
                    if (distance < MIN_DISTANCE) {
                        validPosition = false;
                        break;
                    }
                }

                // Also avoid overlap with villages
                for (Village existingVillage : map.getVillages()) {
                    // Calculate the difference in x-coordinates
                    double dx = existingVillage.getXCoordinate() - x;
                    // Calculate the difference in y-coordinates
                    double dy = existingVillage.getYCoordinate() - y;
                    // Calculate the distance between the village and the mountain
                    double distance = Math.sqrt(dx * dx + dy * dy);

                    // If the distance is less than the minimum distance, the position is not valid
                    if (distance < MIN_DISTANCE) {
                        validPosition = false;
                        break;
                    }
                }

            } while (!validPosition);

            // Add the mountain to the map
            map.addMountain(new Mountain(x, y));
        }



        // Create villages
        for (int i = 0; i < MAX_VILLAGES; i++) {
            // X and Y coordinates for the village
            int x, y;
            // Flag to check if the position is valid
            boolean validPosition;
        
            do {
                // Generate random X and Y coordinates
                x = rand.nextInt(MAX_X / 50) * 50 + 300;
                y = rand.nextInt(MAX_Y / 50) * 50 + 10;

                validPosition = true;
        
                // Check if the new village is too close to any existing villages
                for (Village existing : map.getVillages()) {
                    // Calculate the difference in x-coordinates
                    double dx = existing.getXCoordinate() - x;
                    // Calculate the difference in y-coordinates
                    double dy = existing.getYCoordinate() - y;
                    // Calculate the distance between the two villages
                    double distance = Math.sqrt(dx * dx + dy * dy);
        
                    // If the distance is less than the minimum distance, the position is not valid
                    if (distance < MIN_DISTANCE) {
                        validPosition = false;
                        break;
                    }
                }

                // inside your village do { … } while (!validPosition) loop:
                for (Mountain m : map.getMountains()) {
                    double dx = m.getXCoordinate() - x;
                    double dy = m.getYCoordinate() - y;
                    double dist = Math.sqrt(dx*dx + dy*dy);
                    // use a threshold that covers your mountain radius + half-your-village-size
                    if (dist < MOUNTAIN_RADIUS + 30) {
                        validPosition = false;
                        break;
                    }
                }

        
            } while (!validPosition);
        
            // Create a new village
            Village village = new Village("Village" + i, i, x, y);
            // Add the village to the map
            map.addVillage(village);
        }



        // Create all possible roads between pairs
        List<Village> villages = map.getVillages();
        for (int i = 0; i < villages.size(); i++) {
            for (int j = i + 1; j < villages.size(); j++) {
                // Get the two villages
                Village v1 = villages.get(i);
                Village v2 = villages.get(j);
        
                // Flag to check if the road intersects a mountain
                boolean intersectsMountain = false;
                // Check if the road intersects any mountains
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
         
                // If the road does not intersect any mountains, add it to the list of possible roads
                if (!intersectsMountain) {
                    // Calculate the weight of the road
                    int weight = GameUtils.calculateDistance(v1, v2);
                    // Add the road to the list of possible roads
                    allRoads.add(new Road(v1, v2, weight));
                }
            }
        }
        
        // Build MST roads
        List<Road> mstRoads = MSTBuilder.buildMST(villages, allRoads);
        // Add the MST roads to the map
        for (Road road : mstRoads) {
            map.addRoad(road);
        }


        // Create Enemies 
        for (Road road : mstRoads) {
            if (rand.nextDouble() < enemyChance) {
                // choose a point t∈[0.2,0.8] so enemies aren’t on top of villages
                double t = 0.2 + rand.nextDouble() * 0.6;
                int ex = (int) (road.getFromVillage().getXCoordinate() * (1 - t)
                            + road.getToVillage().getXCoordinate() * t);
                int ey = (int) (road.getFromVillage().getYCoordinate() * (1 - t)
                            + road.getToVillage().getYCoordinate() * t);

                // optional: skip if too close to a mountain or village
                boolean valid = map.getMountains().stream().noneMatch(
                    m -> GameUtils.calculateDistance(ex, ey, m.getXCoordinate(), m.getYCoordinate()) < MOUNTAIN_RADIUS + 20
                );
                if (valid) {
                    map.addEnemy(new Enemy(ex, ey, 1));  // level‑1 enemy
                }
            }
        }

        return map;
    }
    
}

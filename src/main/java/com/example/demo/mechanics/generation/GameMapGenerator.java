/**
 * This class generates the game map.
 */
package com.example.demo.mechanics.generation;

import com.example.demo.classes.Enemy;
import com.example.demo.classes.Mountain;
import com.example.demo.classes.Road;
import com.example.demo.classes.Village;
import com.example.demo.classes.VillageType;
import com.example.demo.mechanics.pathfinding.MSTBuilder;
import com.example.demo.utils.GameUtils;
import com.example.demo.utils.SpriteLoader;

import java.util.ArrayList;
import java.util.List;  
 
import java.util.Random;
 
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
/**
 * This class generates the game map.
 */
@Component
public class GameMapGenerator {

    /**
     * The maximum number of mountains to generate.
     */
    private static final int MAX_MOUNTAINS = 20;
    /**
     * The radius of the mountains, used for collision detection.
     */
    private static final int MOUNTAIN_RADIUS = 34;

    /**
     * The maximum number of villages to generate.
     */
    private static final int MAX_VILLAGES = 40;
    /**
     * The minimum distance between villages, used for collision detection.
     */
    private static final int MIN_DISTANCE = 100;

    /**
     * Maximum X coordinate for village generation.
     */
    private static final int MAX_X = 900 - 80; // My sidebar is 300px.
    /**
     * Maximum Y coordinate for village generation.
     */
    private static final int MAX_Y = 900 - 80;

    /**
     * Maximum percentage of enemy spawning.
     */
    private static double enemyChance = 0.3;



    /**
     * Generates the game map.
     * @return The generated game map.
     */
    public static GameMap generateMap(){
    // Create a new game map
        GameMap map = new GameMap();
        // Create a new random number generator
        Random rand = new Random();

           //Create mountains
           for (int i = 0; i < MAX_MOUNTAINS; i++) {
            /**
             * X and Y coordinates for the mountain
             */
            int x;
            /**
             * X and Y coordinates for the mountain
             */
            int y;
            // Flag to check if the position is valid
            boolean validPosition;

            do {
                // Generate random X and Y coordinates
                x = rand.nextInt(MAX_X / 50) * 50 + 300;
                y = rand.nextInt(MAX_Y / 50) * 50 + 20;
                validPosition = true;

                // Avoid overlap with existing mountains
                for (Mountain existing : map.getMountains()) {
                    double xDifference = existing.getXCoordinate() - x;
                    double yDifference = existing.getYCoordinate() - y;
                    double distance = Math.sqrt(xDifference * xDifference + yDifference * yDifference);

                    // If the distance is less than the minimum distance, the position is not valid
                    if (distance < MIN_DISTANCE) {
                        validPosition = false;
                        break;
                    }
                }

                // Also avoid overlap with villages
                for (Village existingVillage : map.getVillages()) {
                    double villageXDistance = existingVillage.getXCoordinate() - x;
                    double villageYDistance = existingVillage.getYCoordinate() - y;
                    double distance = Math.sqrt(villageXDistance * villageXDistance + villageYDistance * villageYDistance);

                    // If the distance is less than the minimum distance, the position is not valid
                    if (distance < MIN_DISTANCE) {
                        validPosition = false;
                        break;
                    }
                }

            } while (!validPosition);

            // Get a random sprite
            List<String> mountainSprites = SpriteLoader.MOUNTAIN_SPRITES;
            String sprite = mountainSprites.get(rand.nextInt(mountainSprites.size()));
            Mountain mountain = new Mountain(x, y, sprite);

            // Add the mountain to the map
            map.addMountain(mountain);
        }

                List<Village> villages = new ArrayList<>();
        for (int i = 0; i < MAX_VILLAGES; i++) {
            int x, y;
            boolean validPosition;
            do {
                x = rand.nextInt(MAX_X / 50) * 50 + 300;
                y = rand.nextInt(MAX_Y / 50) * 50 + 10;
                validPosition = true;

                for (Village existing : villages) {
                    double dx = existing.getXCoordinate() - x;
                    double dy = existing.getYCoordinate() - y;
                    if (Math.sqrt(dx * dx + dy * dy) < MIN_DISTANCE) {
                        validPosition = false;
                        break;
                    }
                }

                for (Mountain mountain : map.getMountains()) {
                    double dx = mountain.getXCoordinate() - x;
                    double dy = mountain.getYCoordinate() - y;
                    if (Math.sqrt(dx * dx + dy * dy) < MOUNTAIN_RADIUS + 30) {
                        validPosition = false;
                        break;
                    }
                }

            } while (!validPosition);

             // Instead of randomizing each separately
            VillageType type = VillageType.values()[rand.nextInt(VillageType.values().length)];

            String name = switch (type) {
                case COMMON -> "CommonVillage";
                case TIMBER -> "TimberVillage";
                case ARMOR -> "ArmorVillage";
                case COMPOSITE -> "CompositeVillage";
            };

            String sprite = "/assets/villages/" + name.toLowerCase() + ".png";


            int structures = rand.nextInt(10) + 2;
            int maxPop = structures * 5;
            int minPop = structures * 2;
            int population = rand.nextInt(maxPop - minPop + 1) + minPop;

            Village village = new Village("Village" + i, i, population, structures, x, y, sprite, type);
            villages.add(village);
            map.addVillage(village);
        }

        // Select a random village to be the starting village
        Village startingVillage = villages.get(rand.nextInt(villages.size()));
        map.setStartingVillage(startingVillage);

        // Create all possible roads between pairs
        List<Road> allRoads = new ArrayList<>();
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
                            ))  
                    {
                        intersectsMountain = true;
                        break;
                    }
                }
         
                // If the road does not intersect any mountains, I add it to the list of possible roads
                if (!intersectsMountain) {
                    // Calculate the weight of the road
                    int weight = GameUtils.calculateDistance(v1, v2);
                    // Add the road to the list of possible roads
                    allRoads.add(new Road(v1, v2, weight));
                }
            }
        }
        
        // Build MinnumSpanningTree roads (code for my MST found in /mechanics/pathfinding/MSTBuilder.java)
        List<Road> mstRoads = MSTBuilder.buildMST(villages, allRoads);
        // Add the MST roads to the map
        for (Road road : mstRoads) {
            map.addRoad(road);
        }

        // Create Enemies
        for (Road road : mstRoads) {
            
            // Calculate the midpoint of the road
            int enemyXCoordinate = (road.getFromVillage().getXCoordinate() + road.getToVillage().getXCoordinate()) / 2;
            int enemyYCoordinate = (road.getFromVillage().getYCoordinate() + road.getToVillage().getYCoordinate()) / 2;

            // Skip if too close to the starting village
            if (GameUtils.calculateDistance(enemyXCoordinate, enemyYCoordinate, startingVillage.getXCoordinate(), startingVillage.getYCoordinate()) < MIN_DISTANCE) {
                continue;
            }

            if (rand.nextDouble() < enemyChance) {
                // optional: skip if too close to a mountain or village
                boolean valid = map.getMountains().stream().noneMatch(
                    mountain -> GameUtils.calculateDistance(enemyXCoordinate, enemyYCoordinate, mountain.getXCoordinate(), mountain.getYCoordinate()) < MOUNTAIN_RADIUS + 20
                );
                if (valid) {
                    String enemySprite = SpriteLoader.ENEMY_SPRITES.get(rand.nextInt(SpriteLoader.ENEMY_SPRITES.size()));
                    map.addEnemy(new Enemy(enemyXCoordinate, enemyYCoordinate, 1, enemySprite));
                }
            }
        }

        return map;
    }
}

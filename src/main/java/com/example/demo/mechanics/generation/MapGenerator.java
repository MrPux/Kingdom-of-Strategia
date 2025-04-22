
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
import com.example.demo.utils.SpriteLoader;

import java.util.ArrayList;
import java.util.List;  
 
import java.util.Random;

/**
 * This class generates the game map.
 */
public class MapGenerator {

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
     * The minimum distance between villages, to avoid villages stacking.
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
    public static GameMap generateMap() {
        // Create a new game map
        /**
         * The generated game map.
         */
        GameMap map = new GameMap();
        // Create a new random number generator
        /**
         * Random number generator.
         */
        Random rand = new Random();

        // Create a list to hold all possible roads
        /**
         * List of all possible roads.
         */
        List<Road> allRoads = new ArrayList<>();
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
                    /**
                     * The difference in x-coordinates between the existing mountain and the new mountain.
                     */
                    double xDifference = existing.getXCoordinate() - x;
                    /**
                     * The difference in y-coordinates between the existing mountain and the new mountain.
                     */
                    double yDifference = existing.getYCoordinate() - y;
                    // Calculate the distance between the two mountains
                    double distance = Math.sqrt(xDifference * xDifference + yDifference * yDifference);

                    // If the distance is less than the minimum distance, the position is not valid
                    if (distance < MIN_DISTANCE) {
                        validPosition = false;
                        break;
                    }
                }

                // Also avoid overlap with villages
                for (Village existingVillage : map.getVillages()) {
                    /**
                     * The difference in x-coordinates between the existing village and the new mountain.
                     */
                    double villageXDistance = existingVillage.getXCoordinate() - x;
                    /**
                     * The difference in y-coordinates between the existing village and the new mountain.
                     */
                    double villageYDistance = existingVillage.getYCoordinate() - y;
                    // Calculate the distance between the village and the mountain
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
            /**
             * The sprite for the mountain.
             */
            String sprite = mountainSprites.get(rand.nextInt(mountainSprites.size()));
            // Create a new mountain
            Mountain mountain = new Mountain(x, y, sprite);

            // Add the mountain to the map
            map.addMountain(mountain);
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
                for (Mountain mountain : map.getMountains()) {
                    /**
                     * The difference in x-coordinates between the mountain and the potential village.
                     */
                    double mountainXDistance = mountain.getXCoordinate() - x;
                    /**
                     * The difference in y-coordinates between the mountain and the potential village.
                     */
                    double mountainYDistance = mountain.getYCoordinate() - y;
                    double distance = Math.sqrt(mountainXDistance*mountainXDistance + mountainYDistance*mountainYDistance);
                    // use a threshold that covers your mountain radius + half-your-village-size
                    if (distance < MOUNTAIN_RADIUS + 30) {
                        validPosition = false;
                        break;
                    }
                }

        
            } while (!validPosition);

            // Get a random sprite
            String sprite = "/assets/villages/" + SpriteLoader.VILLAGE_SPRITES.get(rand.nextInt(SpriteLoader.VILLAGE_SPRITES.size()));
            // Create a new village
            Village village = new Village("Village" + i, i, x, y, sprite);
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
                /**
                 * A value between 0.2 and 0.8, used to position the enemy along the road.
                 */
                double roadPositionFactor = 0.2 + rand.nextDouble() * 0.6;
                /**
                 * The x-coordinate of the enemy.
                 */
                int enemyXCoordinate = (int) (road.getFromVillage().getXCoordinate() * (1 - roadPositionFactor)
                            + road.getToVillage().getXCoordinate() * roadPositionFactor);
                /**
                 * The y-coordinate of the enemy.
                 */
                int enemyYCoordinate = (int) (road.getFromVillage().getYCoordinate() * (1 - roadPositionFactor)
                            + road.getToVillage().getYCoordinate() * roadPositionFactor);

                // optional: skip if too close to a mountain or village
                /**
                 * Mountain object
                 */
                boolean valid = map.getMountains().stream().noneMatch(
                    mountain -> GameUtils.calculateDistance(enemyXCoordinate, enemyYCoordinate, mountain.getXCoordinate(), mountain.getYCoordinate()) < MOUNTAIN_RADIUS + 20
                );
                if (valid) {
                    map.addEnemy(new Enemy(enemyXCoordinate, enemyYCoordinate, 1));  // level‑1 enemy
                }
            }
        }

        return map;
    }
    
}

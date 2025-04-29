package com.example.demo.classes.villageClasses;

import com.example.demo.utils.SpriteLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * <h1>VillageGenerator</h1>
 * <p>
 * This class is responsible for generating the structure layout of a village.
 * It includes methods for placing structures, roads, and creating negative cycles for gameplay mechanics.
 * </p>
 * <p>
 * The VillageGenerator uses a combination of random placement and forced connections to create
 * interesting and challenging village layouts. It also introduces negative cycles, which can be exploited
 * by the player for strategic advantages.
 * </p>
 */
public class VillageGenerator {

    /**
     * Random number generator for generating village layouts.
     */
    private static final Random rand = new Random();

    /**
     * <h1>generateStructuresForVillage Method</h1>
     * <p>
     * Generates the structures and roads for a given village.
     * This method places a fountain at the center, generates additional structures,
     * creates a basic chain for connectivity, adds random extra connections,
     * and introduces a strong negative cycle to the village structure.
     * </p>
     * <p>
     * The method follows these steps:
     * <ol>
     *   <li>Places a fountain at the center of the village.</li>
     *   <li>Generates a random number of additional structures (3-6).</li>
     *   <li>Forces a basic chain of connections between the structures to ensure connectivity.</li>
     *   <li>Adds random extra connections between structures to create a more complex network.</li>
     *   <li>Introduces a strong negative cycle to the village structure to create strategic gameplay opportunities.</li>
     *   <li>Sets the generated structures and roads into the village object.</li>
     * </ol>
     * </p>
     *
     * @param village The village to generate structures for.
     */
    public static void generateStructuresForVillage(Village village) {
        // ========================= Initialize lists to hold structures and roads =========================
        /**
         * A list to hold the structure nodes in the village.
         */
        List<StructureNode> structures = new ArrayList<>();
        /**
         * A list to hold the roads connecting the structure nodes.
         */
        List<StructureRoad> structureRoads = new ArrayList<>();

        // ========================= 1. Place a fountain at center =========================
        /**
         * Define the center coordinates.
         */
        int centerX = 300; // or compute center of structures
        /**
         * Define the center coordinates.
         */
        int centerY = 300;
        /**
         * Get the fountain sprite based on the village type.
         */
        String fountainSprite = SpriteLoader.getFountainSprite(village.getType());
        /**
         * Create a new structure node for the fountain.
         */
        StructureNode fountain = new StructureNode(structures.size(), centerX, centerY, fountainSprite);
        /**
         * Add the fountain to the list of structures.
         */
        structures.add(fountain);


        // ========================= 2. Generate structures =========================
        /**
         * Generate a random number of structures between 3 and 6.
         */
        int numStructures = rand.nextInt(4) + 3; // 3–6
        /**
         * Iterate to create the specified number of structures.
         */
        for (int i = 1; i <= numStructures; i++) {  // <-- start at 1 now
            /**
             * Generate random x and y coordinates for the structure.
             */
            int x = rand.nextInt(300) + 100;
            /**
             * Generate random x and y coordinates for the structure.
             */
            int y = rand.nextInt(300) + 100;
            /**
             * Pick a random sprite for the structure.
             */
            String sprite = pickRandomStructureSprite(village.getType());
            /**
             * Create a new structure node and add it to the list of structures.
             */
            structures.add(new StructureNode(i, x, y, sprite));
        }


        // ========================= 3. Force basic chain for connectivity =========================
        /**
         * Iterate through the structures to create a basic chain of connections.
         */
        for (int i = 0; i < structures.size() - 1; i++) {
            /**
             * Get the current structure node.
             */
            StructureNode from = structures.get(i);
            /**
             * Get the next structure node.
             */
            StructureNode to = structures.get(i + 1);
            /**
             * Generate a random weight for the road.
             */
            int weight = rand.nextInt(20) - 10;
            /**
             * Connect the two structure nodes.
             */
            from.connectTo(to, weight);
            /**
             * Connect the two structure nodes.
             */
            to.connectTo(from, weight);
            /**
             * Add the new road to the list of structure roads.
             */
            structureRoads.add(new StructureRoad(from, to, weight));
        }

        // ========================= 4. Add random extra connections =========================
        /**
         * Iterate through the structures to add random extra connections.
         */
        for (int i = 1; i < structures.size(); i++) {  // Start from 1
            /**
             * Iterate through the remaining structures.
             */
            for (int j = i + 1; j < structures.size(); j++) {
                /**
                 * Add a connection with a 50% probability.
                 */
                if (rand.nextDouble() < 0.5) {
                    /**
                     * Get the current structure node.
                     */
                    StructureNode from = structures.get(i);
                    /**
                     * Get the next structure node.
                     */
                    StructureNode to = structures.get(j);
                    /**
                     * Generate a random weight for the road.
                     */
                    int weight = rand.nextInt(20) - 10;
                    /**
                     * Connect the two structure nodes.
                     */
                    from.connectTo(to, weight);
                    /**
                     * Connect the two structure nodes.
                     */
                    to.connectTo(from, weight);
                    /**
                     * Add the new road to the list of structure roads.
                     */
                    structureRoads.add(new StructureRoad(from, to, weight));
                }
            }
        }

        // ========================= 5. My Ultimate Trick: Create at least one strong negative cycle =========================
        /**
         * Check if there are enough structures to create a negative cycle.
         */
        if (structures.size() >= 4) { // because size includes fountain
            /**
             * Determine the size of the cycle (3-5).
             */
            int cycleSize = Math.min(rand.nextInt(3) + 3, structures.size() - 1); // -1 for fountain
            /**
             * Create a shuffled list of structure nodes (excluding the fountain).
             */
            List<StructureNode> shuffled = new ArrayList<>(structures.subList(1, structures.size()));
            /**
             * Shuffle the list.
             */
            Collections.shuffle(shuffled, rand);
            /**
             * Get a sublist of nodes to form the cycle.
             */
            List<StructureNode> cycleNodes = shuffled.subList(0, cycleSize);

            /**
             * Generate a strong negative weight for the cycle.
             */
            int strongNegativeWeight = -(rand.nextInt(10) + 5); // -5 to -15

            /**
             * Iterate through the cycle nodes to create the negative cycle.
             */
            for (int i = 0; i < cycleNodes.size(); i++) {
                /**
                 * Get the current structure node.
                 */
                StructureNode from = cycleNodes.get(i);
                /**
                 * Get the next structure node in the cycle.
                 */
                StructureNode to = cycleNodes.get((i + 1) % cycleNodes.size());

                /**
                 * Set the weight for the road. The last road in the cycle gets the strong negative weight.
                 */
                int weight = (i == cycleNodes.size() - 1) ? strongNegativeWeight : rand.nextInt(5) + 1;
                /**
                 * Connect the two structure nodes.
                 */
                from.connectTo(to, weight);

                /**
                 * Create a new structure road.
                 */
                StructureRoad specialRoad = new StructureRoad(from, to, weight);

                /**
                 * Mark the last road in the cycle as a negative cycle.
                 */
                if (i == cycleNodes.size() - 1) {
                    specialRoad.markAsNegativeCycle(); // ✅ Last one is negative
                }

                /**
                 * Add the new road to the list of structure roads.
                 */
                structureRoads.add(specialRoad);
            }
        }


        // ========================= 6. Set into village =========================
        /**
         * Set the generated structures and roads into the village object.
         */
        village.setStructuresList(structures);
        village.setStructureRoads(structureRoads);
    }

    /**
     * <h1>pickRandomStructureSprite Method</h1>
     * <p>
     * Picks a random structure sprite based on the village type.
     * </p>
     *
     * @param type The type of the village.
     * @return A string representing the sprite for the structure.
     */
    // ✅ Updated to accept VillageType
    /**
     * Picks a random structure sprite based on the village type.
     * </p>
     *
     * @param type The type of the village.
     * @return A string representing the sprite for the structure.
     */
    // ✅ Updated to accept VillageType
    private static String pickRandomStructureSprite(VillageType type) {
        // ========================= Get a random structure sprite from the SpriteLoader =========================
        return SpriteLoader.getRandomStructureSprite(type);
    }
}

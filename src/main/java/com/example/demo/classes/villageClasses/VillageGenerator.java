package com.example.demo.classes.villageClasses;

import com.example.demo.utils.SpriteLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This class is responsible for generating the structure layout of a village.
 * It includes methods for placing structures, roads, and creating negative cycles for gameplay mechanics.
 */
public class VillageGenerator {

    private static final Random rand = new Random();

    /**
     * Generates the structures and roads for a given village.
     * This method places a fountain at the center, generates additional structures,
     * creates a basic chain for connectivity, adds random extra connections,
     * and introduces a strong negative cycle to the village structure.
     *
     * @param village The village to generate structures for.
     */
    public static void generateStructuresForVillage(Village village) {
        List<StructureNode> structures = new ArrayList<>();
        List<StructureRoad> structureRoads = new ArrayList<>();
        // Place a fountain at center
        int centerX = 300; // or compute center of structures
        int centerY = 300;
        String fountainSprite = SpriteLoader.getFountainSprite(village.getType());
        StructureNode fountain = new StructureNode(structures.size(), centerX, centerY, fountainSprite);
        structures.add(fountain);


        // 1. Generate structures 
        int numStructures = rand.nextInt(4) + 3; // 3–6
        for (int i = 1; i <= numStructures; i++) {  // <-- start at 1 now
            int x = rand.nextInt(300) + 100;
            int y = rand.nextInt(300) + 100;
            String sprite = pickRandomStructureSprite(village.getType());
            structures.add(new StructureNode(i, x, y, sprite));
        }


        // 2. Force basic chain for connectivity
        for (int i = 0; i < structures.size() - 1; i++) {
            StructureNode from = structures.get(i);
            StructureNode to = structures.get(i + 1);
            int weight = rand.nextInt(20) - 10;
            from.connectTo(to, weight);
            to.connectTo(from, weight);
            structureRoads.add(new StructureRoad(from, to, weight));
        }

        // 3. Add random extra connections
        for (int i = 1; i < structures.size(); i++) {  // Start from 1
            for (int j = i + 1; j < structures.size(); j++) {
                if (rand.nextDouble() < 0.5) {
                    StructureNode from = structures.get(i);
                    StructureNode to = structures.get(j);
                    int weight = rand.nextInt(20) - 10;
                    from.connectTo(to, weight);
                    to.connectTo(from, weight);
                    structureRoads.add(new StructureRoad(from, to, weight));
                }
            }
        }

        // 4. Ultra Trick: Create at least one strong negative cycle
        if (structures.size() >= 4) { // because size includes fountain
            int cycleSize = Math.min(rand.nextInt(3) + 3, structures.size() - 1); // -1 for fountain
            List<StructureNode> shuffled = new ArrayList<>(structures.subList(1, structures.size()));
            Collections.shuffle(shuffled, rand);
            List<StructureNode> cycleNodes = shuffled.subList(0, cycleSize);

            int strongNegativeWeight = -(rand.nextInt(10) + 5); // -5 to -15

            for (int i = 0; i < cycleNodes.size(); i++) {
                StructureNode from = cycleNodes.get(i);
                StructureNode to = cycleNodes.get((i + 1) % cycleNodes.size());

                int weight = (i == cycleNodes.size() - 1) ? strongNegativeWeight : rand.nextInt(5) + 1;
                from.connectTo(to, weight);

                StructureRoad specialRoad = new StructureRoad(from, to, weight);

                if (i == cycleNodes.size() - 1) {
                    specialRoad.markAsNegativeCycle(); // ✅ Last one is negative
                }

                structureRoads.add(specialRoad);
            }
        }


        // 5. Set into village
        village.setStructuresList(structures);
        village.setStructureRoads(structureRoads);
    }

    /**
     * Picks a random structure sprite based on the village type.
     *
     * @param type The type of the village.
     * @return A string representing the sprite for the structure.
     */
    // ✅ Updated to accept VillageType
    private static String pickRandomStructureSprite(VillageType type) {
        return SpriteLoader.getRandomStructureSprite(type);
    }
}

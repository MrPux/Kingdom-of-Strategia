package com.example.demo.mechanics.generation;

import com.example.demo.classes.Enemy;
import com.example.demo.classes.Mountain;
import com.example.demo.classes.Road;
import com.example.demo.classes.villageClasses.StructureNode;
import com.example.demo.classes.villageClasses.Village;
import com.example.demo.classes.villageClasses.VillageType;
import com.example.demo.mechanics.pathfinding.MSTBuilder;
import com.example.demo.utils.GameUtils;
import com.example.demo.utils.SpriteLoader;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Generates the game map with villages, mountains, roads, and enemies.
 */
@Component
public class GameMapGenerator {

    private static final int MAX_MOUNTAINS = 20;
    private static final int MOUNTAIN_RADIUS = 34;
    private static final int MAX_VILLAGES = 40;
    private static final int MIN_DISTANCE = 100;
    private static final int MAX_X = 820; // 900 - 80, map width minus sidebar
    private static final int MAX_Y = 820;
    private static final double ENEMY_SPAWN_CHANCE = 0.5;

    /**
     * Generates a new game map with mountains, villages, roads, and enemies.
     *
     * @return The generated game map.
     */
    public static GameMap generateMap() {
        GameMap map = new GameMap();
        Random rand = new Random();

        generateMountains(map, rand);
        List<Village> villages = generateVillages(map, rand);
        generateRoadsAndEnemies(map, villages, rand);

        return map;
    }

    /**
     * Generates mountains on the map.
     *
     * @param map  The game map to add mountains to.
     * @param rand The random number generator.
     */
    private static void generateMountains(GameMap map, Random rand) {
        for (int i = 0; i < MAX_MOUNTAINS; i++) {
            int x, y;
            boolean validPosition;
            do {
                x = rand.nextInt(MAX_X / 50) * 50 + 300;
                y = rand.nextInt(MAX_Y / 50) * 50 + 10;
                validPosition = isValidMountainPosition(map, x, y);
            } while (!validPosition);

            String sprite = SpriteLoader.MOUNTAIN_SPRITES.get(rand.nextInt(SpriteLoader.MOUNTAIN_SPRITES.size()));
            map.addMountain(new Mountain(x, y, sprite));
        }
    }

    /**
     * Checks if a given position is valid for placing a mountain.
     *
     * @param map The game map.
     * @param x   The x-coordinate of the mountain.
     * @param y   The y-coordinate of the mountain.
     * @return True if the position is valid, false otherwise.
     */
    private static boolean isValidMountainPosition(GameMap map, int x, int y) {
        for (Mountain mountain : map.getMountains()) {
            if (GameUtils.calculateDistance(x, y, mountain.getXCoordinate(), mountain.getYCoordinate()) < MIN_DISTANCE) {
                return false;
            }
        }
        for (Village village : map.getVillages()) {
            if (GameUtils.calculateDistance(x, y, village.getXCoordinate(), village.getYCoordinate()) < MIN_DISTANCE) {
                return false;
            }
        }
        return true;
    }

    /**
     * Generates villages on the map.
     *
     * @param map  The game map to add villages to.
     * @param rand The random number generator.
     * @return The list of generated villages.
     */
    private static List<Village> generateVillages(GameMap map, Random rand) {
        List<Village> villages = new ArrayList<>();

        for (int i = 0; i < MAX_VILLAGES; i++) {
            int x, y;
            boolean validPosition;
            do {
                x = rand.nextInt(MAX_X / 50) * 50 + 300;
                y = rand.nextInt(MAX_Y / 50) * 50 + 10;
                validPosition = isValidVillagePosition(map, villages, x, y);
            } while (!validPosition);

            VillageType type = VillageType.values()[rand.nextInt(VillageType.values().length)];
            String name = switch (type) {
                case COMMON -> "CommonVillage";
                case TIMBER -> "TimberVillage";
                case ARMOR -> "ArmorVillage";
                case COMPOSITE -> "CompositeVillage";
            };
            String sprite = "/assets/villages/" + name.toLowerCase() + ".png";

            int structures = rand.nextInt(10) + 2;
            Village village = new Village("Village" + i, i, structures, x, y, sprite, type);

            generateStructuresForVillage(village, structures, rand);

            villages.add(village);
            map.addVillage(village);
        }

        // Pick a random starting village
        Village startingVillage = villages.get(rand.nextInt(villages.size()));
        map.setStartingVillage(startingVillage);

        return villages;
    }

    /**
     * Checks if a given position is valid for placing a village.
     *
     * @param map      The game map.
     * @param villages The list of existing villages.
     * @param x        The x-coordinate of the village.
     * @param y        The y-coordinate of the village.
     * @return True if the position is valid, false otherwise.
     */
    private static boolean isValidVillagePosition(GameMap map, List<Village> villages, int x, int y) {
        for (Village existing : villages) {
            if (GameUtils.calculateDistance(x, y, existing.getXCoordinate(), existing.getYCoordinate()) < MIN_DISTANCE) {
                return false;
            }
        }
        for (Mountain mountain : map.getMountains()) {
            if (GameUtils.calculateDistance(x, y, mountain.getXCoordinate(), mountain.getYCoordinate()) < MOUNTAIN_RADIUS + 30) {
                return false;
            }
        }
        return true;
    }

    /**
     * Generates structure nodes for a given village.
     *
     * @param village   The village to generate structures for.
     * @param nodeCount The number of structure nodes to generate.
     * @param rand      The random number generator.
     */
    private static void generateStructuresForVillage(Village village, int nodeCount, Random rand) {
        List<StructureNode> nodes = new ArrayList<>();
    
        // Step 0: Determine if this is a full positive graph
        boolean fullPositiveMode = false;
        if (nodeCount >= 7) {
            fullPositiveMode = rand.nextDouble() < 0.3;
            if (fullPositiveMode) {
                System.out.println("✨ Full Positive Structure Graph for Village " + village.getName());
            }
        }
    
        // Step 1: Create structure nodes
        for (int id = 0; id < nodeCount; id++) {
            int structureX = rand.nextInt(250) + 50;
            int structureY = rand.nextInt(250) + 50;
            String sprite = SpriteLoader.getRandomStructureSprite(village.getType());
            nodes.add(new StructureNode(id, structureX, structureY, sprite));
        }
    
        // Step 2: FORCE FULL CONNECTIVITY — chain all nodes in sequence
        for (int i = 0; i < nodes.size() - 1; i++) {
            StructureNode from = nodes.get(i);
            StructureNode to = nodes.get(i + 1);
    
            int weight = rand.nextInt(15) - 5;
            if (fullPositiveMode) weight = Math.abs(weight) + 1;
    
            from.connectTo(to, weight);
        }
    
        // Step 3: Add extra random connections (optional)
        for (StructureNode node : nodes) {
            int edgesToAdd = 1 + rand.nextInt(2); // Add 1–2 extra edges
            for (int j = 0; j < edgesToAdd; j++) {
                StructureNode to = nodes.get(rand.nextInt(nodeCount));
    
                if (to != node) { // avoid self-loops unless you want them
                    int weight = rand.nextInt(15) - 5;
                    if (fullPositiveMode) weight = Math.abs(weight) + 1;
                    node.connectTo(to, weight);
                }
            }
        }
    
        // Step 4: Add negative cycle (optional)
        if (nodeCount >= 3) {
            StructureNode a = nodes.get(0);
            StructureNode b = nodes.get(1);
            StructureNode c = nodes.get(2);
    
            int weight1 = fullPositiveMode ? 2 : 2;
            int weight2 = fullPositiveMode ? 2 : 2;
            int weight3 = fullPositiveMode ? 10 : -10; // close cycle
    
            a.connectTo(b, weight1);
            b.connectTo(c, weight2);
            c.connectTo(a, weight3);
        }
    
        // Step 5: Save nodes to village
        village.getStructuresList().addAll(nodes);
        village.getStructureGraph().addAll(nodes);
    }
    

    /**
     * Generates roads and enemies on the map.
     *
     * @param map      The game map to add roads and enemies to.
     * @param villages The list of villages on the map.
     * @param rand     The random number generator.
     */
    private static void generateRoadsAndEnemies(GameMap map, List<Village> villages, Random rand) {
        List<Road> allRoads = new ArrayList<>();

        for (int i = 0; i < villages.size(); i++) {
            for (int j = i + 1; j < villages.size(); j++) {
                Village v1 = villages.get(i);
                Village v2 = villages.get(j);

                if (!doesRoadIntersectMountain(map, v1, v2)) {
                    int weight = GameUtils.calculateDistance(v1, v2);
                    allRoads.add(new Road(v1, v2, weight));
                }
            }
        }

        List<Road> mstRoads = MSTBuilder.buildMST(villages, allRoads);

        for (Road road : mstRoads) {
            map.addRoad(road);
        }

        createEnemiesOnRoads(map, mstRoads, map.getStartingVillage(), rand);
    }

    /**
     * Checks if a road between two villages intersects a mountain.
     *
     * @param map The game map.
     * @param v1  The first village.
     * @param v2  The second village.
     * @return True if the road intersects a mountain, false otherwise.
     */
    private static boolean doesRoadIntersectMountain(GameMap map, Village v1, Village v2) {
        for (Mountain mountain : map.getMountains()) {
            if (GameUtils.lineIntersectsCircle(
                    v1.getXCoordinate(), v1.getYCoordinate(),
                    v2.getXCoordinate(), v2.getYCoordinate(),
                    mountain.getXCoordinate(), mountain.getYCoordinate(),
                    MOUNTAIN_RADIUS
            )) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates enemies on the roads.
     *
     * @param map           The game map to add enemies to.
     * @param mstRoads      The list of roads on the map.
     * @param startingVillage The starting village.
     * @param rand          The random number generator.
     */
    private static void createEnemiesOnRoads(GameMap map, List<Road> mstRoads, Village startingVillage, Random rand) {
        for (Road road : mstRoads) {
            int midX = (road.getFromVillage().getXCoordinate() + road.getToVillage().getXCoordinate()) / 2;
            int midY = (road.getFromVillage().getYCoordinate() + road.getToVillage().getYCoordinate()) / 2;

            if (GameUtils.calculateDistance(midX, midY, startingVillage.getXCoordinate(), startingVillage.getYCoordinate()) < MIN_DISTANCE) {
                continue;
            }

            if (rand.nextDouble() < ENEMY_SPAWN_CHANCE) {
                boolean valid = map.getMountains().stream()
                        .noneMatch(mountain -> GameUtils.calculateDistance(midX, midY, mountain.getXCoordinate(), mountain.getYCoordinate()) < MOUNTAIN_RADIUS + 20);

                if (valid) {
                    int distanceFromStart = GameUtils.calculateDistance(midX, midY, startingVillage.getXCoordinate(), startingVillage.getYCoordinate());
                    int level = Math.max(1, distanceFromStart / 100);
                    String sprite = SpriteLoader.ENEMY_SPRITES.get(rand.nextInt(SpriteLoader.ENEMY_SPRITES.size()));
                    map.addEnemy(new Enemy(midX, midY, level, sprite));
                }
            }
        }
    }
}

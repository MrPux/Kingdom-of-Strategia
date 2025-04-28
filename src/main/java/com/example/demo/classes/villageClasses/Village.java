package com.example.demo.classes.villageClasses;

import com.example.demo.mechanics.pathfinding.BellmanFord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * <h1>Village</h1>
 * <p>
 * Represents a village in the game.
 * This class holds information about the village, such as its name, ID, population, resources,
 * and the structures within it. It also manages the connections between structures for pathfinding purposes.
 * </p>
 * <p>
 * The Village class is a central entity in the game, representing a settlement with its own
 * characteristics and resources. It contains methods for managing resources, structures, and
 * connections between structures.
 * </p>
 */
public class Village {

    private String name;
    private int id;
    private int population;
    private int structures; // Just the number of structures
    private List<StructureNode> structureGraph = new ArrayList<>(); // (For Bellman-Ford checking)
    private List<StructureNode> structuresList = new ArrayList<>(); // (Actual structures inside village)
    private List<StructureRoad> structureRoads = new ArrayList<>(); // (Connections between structures)

    private int xCoordinate;
    private int yCoordinate;
    private String sprite;
    private VillageType type;

    private int knights;
    private int villagers;
    private int wood;
    private int food;
    private int iron;
    private int coal;
    private int rocks;

    /**
     * <h1>Village Constructor</h1>
     * <p>
     * Constructs a new Village object.
     * </p>
     *
     * @param name       The name of the village.
     * @param id         The ID of the village.
     * @param structures The number of structures in the village.
     * @param x          The x-coordinate of the village on the game map.
     * @param y          The y-coordinate of the village on the game map.
     * @param sprite     The sprite representing the village.
     * @param type       The type of the village (e.g., COMMON, TIMBER, ARMOR, COMPOSITE).
     */
    public Village(String name, int id, int structures, int x, int y, String sprite, VillageType type) {
        this.name = name;
        this.id = id;
        this.structures = structures;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.sprite = sprite;
        this.type = type;
        assignResourcesByType(type);
        this.population = this.villagers + this.knights;
    }

    /**
     * <h1>assignResourcesByType Method</h1>
     * <p>
     * Assigns initial resource values to the village based on its type.
     * </p>
     * <p>
     * The resource assignment is based on the village type, with different types having different
     * initial resource distributions. For example, a TIMBER village will have more wood than other types.
     * </p>
     *
     * @param type The type of the village.
     */
    private void assignResourcesByType(VillageType type) {
        Random rand = new Random();

        switch (type) {
            case COMMON:
                this.knights = rand.nextInt(4) + 4;       // 4–7
                this.villagers = rand.nextInt(5) + 5;     // 5–9
                this.wood = rand.nextInt(5) + 6;          // 6–10
                this.food = rand.nextInt(4) + 6;          // 6–9
                this.iron = rand.nextInt(3) + 4;          // 4–6
                this.coal = rand.nextInt(2) + 4;          // 4–5
                this.rocks = rand.nextInt(4) + 4;         // 4–7
                break;

            case TIMBER:
                this.knights = rand.nextInt(3) + 4;       // 4–6
                this.villagers = rand.nextInt(4) + 6;     // 6–9
                this.wood = rand.nextInt(15) + 15;        // 15–29
                this.food = rand.nextInt(4) + 6;          // 6–9
                this.iron = rand.nextInt(2) + 4;          // 4–5
                this.coal = rand.nextInt(2) + 4;          // 4–5
                this.rocks = rand.nextInt(3) + 4;         // 4–6
                break;

            case ARMOR:
                this.knights = rand.nextInt(8) + 18;      // 18–25
                this.villagers = rand.nextInt(4) + 6;     // 6–9
                this.wood = rand.nextInt(3) + 4;          // 4–6
                this.food = rand.nextInt(3) + 4;          // 4–6
                this.iron = rand.nextInt(6) + 10;         // 10–15
                this.coal = rand.nextInt(5) + 8;          // 8–12
                this.rocks = rand.nextInt(4) + 6;         // 6–9
                break;

            case COMPOSITE:
                this.knights = rand.nextInt(9) + 20;      // 20–28
                this.villagers = rand.nextInt(9) + 20;
                this.wood = rand.nextInt(9) + 20;
                this.food = rand.nextInt(9) + 20;
                this.iron = rand.nextInt(9) + 20;
                this.coal = rand.nextInt(9) + 20;
                this.rocks = rand.nextInt(9) + 20;
                break;
        }

        this.population = this.knights + this.villagers;
    }

    // Getters

    /**
     * Gets the name of the village.
     *
     * @return The name of the village.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the ID of the village.
     *
     * @return The ID of the village.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the population of the village.
     *
     * @return The population of the village.
     */
    public int getPopulation() {
        return population;
    }

    /**
     * Gets the number of structures in the village.
     *
     * @return The number of structures in the village.
     */
    public int getStructures() {
        return structures;
    }

    /**
     * Gets the x-coordinate of the village.
     *
     * @return The x-coordinate of the village.
     */
    public int getXCoordinate() {
        return xCoordinate;
    }

    /**
     * Gets the y-coordinate of the village.
     *
     * @return The y-coordinate of the village.
     */
    public int getYCoordinate() {
        return yCoordinate;
    }

    /**
     * Gets the sprite of the village.
     *
     * @return The sprite of the village.
     */
    public String getSprite() {
        return sprite;
    }

    /**
     * Gets the type of the village.
     *
     * @return The type of the village.
     */
    public VillageType getType() {
        return type;
    }

    /**
     * Gets the village type.
     *
     * @return The village type.
     */
    public VillageType getVillageType() {
        return type;
    }

    /**
     * Gets the number of knights in the village.
     *
     * @return The number of knights in the village.
     */
    public int getKnights() {
        return knights;
    }

    /**
     * Gets the number of villagers in the village.
     *
     * @return The number of villagers in the village.
     */
    public int getVillagers() {
        return villagers;
    }

    /**
     * Gets the amount of wood in the village.
     *
     * @return The amount of wood in the village.
     */
    public int getWood() {
        return wood;
    }

    /**
     * Gets the amount of food in the village.
     *
     * @return The amount of food in the village.
     */
    public int getFood() {
        return food;
    }

    /**
     * Gets the amount of iron in the village.
     *
     * @return The amount of iron in the village.
     */
    public int getIron() {
        return iron;
    }

    /**
     * Gets the amount of coal in the village.
     *
     * @return The amount of coal in the village.
     */
    public int getCoal() {
        return coal;
    }

    /**
     * Gets the amount of rocks in the village.
     *
     * @return The amount of rocks in the village.
     */
    public int getRocks() {
        return rocks;
    }

    /**
     * Gets the structure graph of the village.
     *
     * @return The structure graph of the village.
     */
    public List<StructureNode> getStructureGraph() {
        return structureGraph;
    }

    /**
     * Gets the list of structures in the village.
     *
     * @return The list of structures in the village.
     */
    public List<StructureNode> getStructuresList() {
        return structuresList;
    }

    /**
     * Gets the list of structure roads in the village.
     *
     * @return The list of structure roads in the village.
     */
    public List<StructureRoad> getStructureRoads() {
        return structureRoads;
    }

    /**
     * Gets the formatted name of the village based on its sprite.
     *
     * @return The formatted name of the village.
     */
    public String getFormattedName() {
        String base = sprite.substring(sprite.lastIndexOf("/") + 1).replace(".png", "");
        return Arrays.stream(base.split("[-_]"))
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .collect(Collectors.joining(" "));
    }

    /**
     * Checks if the village has a negative cycle in its structure graph.
     *
     * @return True if the village has a negative cycle, false otherwise.
     */
    public boolean hasNegativeCycle() {
        return BellmanFord.hasNegativeCycle(this.structureGraph);
    }

    // Setters

    /**
     * Sets the name of the village.
     *
     * @param name The new name of the village.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the ID of the village.
     *
     * @param id The new ID of the village.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the x-coordinate of the village.
     *
     * @param xCoordinate The new x-coordinate of the village.
     */
    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /**
     * Sets the y-coordinate of the village.
     *
     * @param yCoordinate The new y-coordinate of the village.
     */
    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /**
     * Sets the type of the village.
     *
     * @param type The new type of the village.
     */
    public void setType(VillageType type) {
        this.type = type;
    }

    /**
     * Sets the list of structures in the village.
     *
     * @param structuresList The new list of structures in the village.
     */
    public void setStructuresList(List<StructureNode> structuresList) {
        this.structuresList = structuresList;
    }

    /**
     * Sets the list of structure roads in the village.
     *
     * @param structureRoads The new list of structure roads in the village.
     */
    public void setStructureRoads(List<StructureRoad> structureRoads) {
        this.structureRoads = structureRoads;
    }

    /**
     * Adds a structure node to the village's structure graph.
     *
     * @param node The structure node to add.
     */
    public void addStructureNode(StructureNode node) {
        this.structureGraph.add(node);
    }

    /**
     * Sets the structure graph of the village.
     *
     * @param structureGraph The new structure graph of the village.
     */
    public void setStructureGraph(List<StructureNode> structureGraph) {
        this.structureGraph = structureGraph;
    }
}

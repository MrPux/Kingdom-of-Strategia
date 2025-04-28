package com.example.demo.classes.villageClasses;

/**
 * <h1>StructureRoad</h1>
 * <p>
 * Represents a road connecting two structure nodes within a village.
 * Each structure road has a source structure, a destination structure, a weight representing the cost or distance,
 * and a flag indicating whether it is part of a negative cycle.
 * </p>
 * <p>
 * The StructureRoad class represents a connection between two structure nodes in the village structure graph.
 * It stores information about the source and destination nodes, the weight of the connection,
 * and whether the road is part of a negative cycle, which can have strategic implications in the game.
 * </p>
 */
public class StructureRoad {

    private final StructureNode fromStructure;
    private final StructureNode toStructure;
    private final int weight;
    private boolean isNegativeCycle; // ✅ Mark if this is special

    /**
     * <h1>StructureRoad Constructor</h1>
     * <p>
     * Constructs a new StructureRoad object.
     * </p>
     *
     * @param from The source structure node.
     * @param to   The destination structure node.
     * @param weight The weight of the road (e.g., distance, cost).
     */
    public StructureRoad(StructureNode from, StructureNode to, int weight) {
        this.fromStructure = from;
        this.toStructure = to;
        this.weight = weight;
        this.isNegativeCycle = false; // Default normal
    }

    /**
     * <h1>markAsNegativeCycle Method</h1>
     * <p>
     * Marks this structure road as part of a negative cycle.
     * This is used to identify roads that contribute to a negative cycle in the village's structure graph.
     * </p>
     * <p>
     * Negative cycles can be exploited by the player for strategic advantages, such as creating
     * infinite resource loops or weakening enemy defenses.
     * </p>
     */
    // ✅ Method to mark it as a negative cycle
    public void markAsNegativeCycle() {
        this.isNegativeCycle = true;
    }

    /**
     * <h1>isNegativeCycle Method</h1>
     * <p>
     * Checks if this structure road is part of a negative cycle.
     * </p>
     *
     * @return True if this road is part of a negative cycle, false otherwise.
     */
    // ✅ Getter to check if it's part of a negative cycle
    public boolean isNegativeCycle() {
        return isNegativeCycle;
    }

    /**
     * Gets the source structure node of this road.
     *
     * @return The source structure node.
     */
    // ✅ Getters for fields
    public StructureNode getFromStructure() {
        return fromStructure;
    }

    /**
     * Gets the destination structure node of this road.
     *
     * @return The destination structure node.
     */
    public StructureNode getToStructure() {
        return toStructure;
    }

    /**
     * Gets the weight of this road.
     *
     * @return The weight of this road.
     */
    public int getWeight() {
        return weight;
    }
}

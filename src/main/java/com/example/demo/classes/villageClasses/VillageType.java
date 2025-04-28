package com.example.demo.classes.villageClasses;

/**
 * <h1>VillageType</h1>
 * <p>
 * Represents the different types of villages in the game.
 * Each type has different characteristics and resource distributions.
 * </p>
 * <p>
 * The VillageType enum is used to define the different types of villages in the game,
 * each with its own unique characteristics and resource distributions. This allows for
 * varied gameplay and strategic decision-making.
 * </p>
 */
public enum VillageType {
    /**
     * <h1>COMMON</h1>
     * <p>
     * Represents a common village type with balanced resources.
     * </p>
     * <p>
     * Common villages have a balanced distribution of resources, making them a good starting point
     * for players.
     * </p>
     */
    COMMON,
    /**
     * <h1>TIMBER</h1>
     * <p>
     * Represents a timber village type with abundant wood resources.
     * </p>
     * <p>
     * Timber villages have a large supply of wood, making them ideal for constructing buildings
     * and other structures that require wood.
     * </p>
     */
    TIMBER,
    /**
     * <h1>ARMOR</h1>
     * <p>
     * Represents an armor village type with strong defensive capabilities and iron resources.
     * </p>
     * <p>
     * Armor villages have strong defensive capabilities and a large supply of iron, making them
     * well-suited for military operations and defense.
     * </p>
     */
    ARMOR,
    /**
     * <h1>COMPOSITE</h1>
     * <p>
     * Represents a composite village type with a mix of all resources.
     * </p>
     * <p>
     * Composite villages have a mix of all resources, making them self-sufficient and able to
     * adapt to different situations.
     * </p>
     */
    COMPOSITE
}

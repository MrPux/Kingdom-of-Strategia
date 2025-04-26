package com.example.demo.classes;

/**
 * Represents the different types of roads available in the game.
 * Each road type has different attributes, such as speed boost and resource bonuses.
 */
public enum RoadType {
    /**
     * Represents a road with no special attributes.
     */
    NONE(0, 0, 0, 0, 0, 0, 0, 0),
    /**
     * Represents a dirt path with a small bonus to food and laborers.
     */
    DIRT_PATH(0, 1, 0, 0, 0, 1, 0, 0),
    /**
     * Represents a gravel road with a moderate bonus to rocks and laborers.
     */
    GRAVEL_ROAD(10, 2, 0, 2, 0, 2, 0, 0),
    /**
     * Represents a stone road with a significant bonus to rocks and coal.
     */
    STONE_ROAD(25, 2, 0, 4, 3, 0, 0, 0),
    /**
     * Represents a trade route with bonuses to food, rocks, coal, laborers, and knights.
     */
    TRADE_ROUTE(40, 2, 1, 6, 4, 3, 0, 1),
    /**
     * Represents a royal road with significant bonuses to food, iron, rocks, coal, laborers, knights, and wood.
     */
    ROYAL_ROAD(60, 2, 2, 8, 5, 5, 2, 3); // Boost, food, iron, rocks, coal, laborers, knights

    /**
     * The speed boost provided by the road type.
     */
    public final int speedBoost;
    /**
     * The food bonus provided by the road type.
     */
    public final int food;
    /**
     * The iron bonus provided by the road type.
     */
    public final int iron;
    /**
     * The rocks bonus provided by the road type.
     */
    public final int rocks;
    /**
     * The coal bonus provided by the road type.
     */
    public final int coal;
    /**
     * The laborers bonus provided by the road type.
     */
    public final int laborers;
    /**
     * The knights bonus provided by the road type.
     */
    public final int knights;
    /**
     * The wood bonus provided by the road type.
     */
    public final int wood;

    /**
     * Constructs a RoadType object.
     *
     * @param speedBoost The speed boost provided by the road type.
     * @param food       The food bonus provided by the road type.
     * @param iron       The iron bonus provided by the road type.
     * @param rocks      The rocks bonus provided by the road type.
     * @param coal       The coal bonus provided by the road type.
     * @param laborers   The laborers bonus provided by the road type.
     * @param knights    The knights bonus provided by the road type.
     * @param wood       The wood bonus provided by the road type.
     */
    RoadType(int speedBoost, int food, int iron, int rocks, int coal, int laborers, int knights, int wood) {
        this.speedBoost = speedBoost;
        this.food = food;
        this.iron = iron;
        this.rocks = rocks;
        this.coal = coal;
        this.laborers = laborers;
        this.knights = knights;
        this.wood = wood;
    }


}

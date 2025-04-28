package com.example.demo.classes;

/**
 * <h1>RoadType</h1>
 * <p>
 * Represents the different types of roads available in the game.
 * Each road type has different attributes, such as speed boost and resource bonuses.
 * </p>
 * <p>
 * The RoadType enum is used to define the different types of roads in the game,
 * each with its own unique attributes and bonuses. This allows for varied gameplay
 * and strategic decision-making related to road construction and management.
 * </p>
 */
public enum RoadType {
    /**
     * <h1>NONE</h1>
     * <p>
     * Represents a road with no special attributes.
     * </p>
     * <p>
     * This road type provides no speed boost or resource bonuses.
     * </p>
     */
    NONE(0, 0, 0, 0, 0, 0, 0, 0),
    /**
     * <h1>DIRT_PATH</h1>
     * <p>
     * Represents a dirt path with a small bonus to food and laborers.
     * </p>
     * <p>
     * Dirt paths provide a small bonus to food production and the number of available laborers.
     * </p>
     */
    DIRT_PATH(0, 1, 0, 0, 0, 1, 0, 0),
    /**
     * <h1>GRAVEL_ROAD</h1>
     * <p>
     * Represents a gravel road with a moderate bonus to rocks and laborers.
     * </p>
     * <p>
     * Gravel roads provide a moderate bonus to rock production and the number of available laborers.
     * </p>
     */
    GRAVEL_ROAD(10, 2, 0, 2, 0, 2, 0, 0),
    /**
     * <h1>STONE_ROAD</h1>
     * <p>
     * Represents a stone road with a significant bonus to rocks and coal.
     * </p>
     * <p>
     * Stone roads provide a significant bonus to rock and coal production.
     * </p>
     */
    STONE_ROAD(25, 2, 0, 4, 3, 0, 0, 0),
    /**
     * <h1>TRADE_ROUTE</h1>
     * <p>
     * Represents a trade route with bonuses to food, rocks, coal, laborers, and knights.
     * </p>
     * <p>
     * Trade routes provide bonuses to food production, rock and coal production, the number of available laborers,
     * and the number of available knights.
     * </p>
     */
    TRADE_ROUTE(40, 2, 1, 6, 4, 3, 0, 1),
    /**
     * <h1>ROYAL_ROAD</h1>
     * <p>
     * Represents a royal road with significant bonuses to food, iron, rocks, coal, laborers, knights, and wood.
     * </p>
     * <p>
     * Royal roads provide significant bonuses to food production, iron production, rock and coal production,
     * the number of available laborers, the number of available knights, and wood production.
     * </p>
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

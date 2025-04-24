package com.example.demo.classes;

public enum RoadType {
    NONE(0,0,0,0,0,0,0,0),
    DIRT_PATH(0, 1, 0, 0, 0, 1, 0, 0),
    GRAVEL_ROAD(10, 2, 0, 2, 0, 2, 0, 0),
    STONE_ROAD(25, 2, 0, 4, 3, 0, 0, 0),
    TRADE_ROUTE(40, 2, 1, 6, 4, 3, 0, 1),
    ROYAL_ROAD(60, 2, 2, 8, 5, 5, 2, 3); // Boost, food, iron, rocks, coal, laborers, knights

    public final int speedBoost;
    public final int food;
    public final int iron;
    public final int rocks;
    public final int coal;
    public final int laborers;
    public final int knights;
    public final int wood;

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

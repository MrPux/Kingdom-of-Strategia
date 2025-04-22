package com.example.demo.classes;

/**
 * Represents an enemy in the game.
 */
public class Enemy {
    /**
     * The x-coordinate of the enemy.
     */
    private final int xCoordinate;
    /**
     * The y-coordinate of the enemy.
     */
    private final int yCoordinate;
    /**
     * The level of the enemy.
     */
    private final int level;

    /**
     * Constructs an Enemy object.
     * @param x The x-coordinate of the enemy.
     * @param y The y-coordinate of the enemy.
     * @param level The level of the enemy.
     */
    public Enemy(int x, int y, int level) {
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.level = level;
    }

    /**
     * Gets the x-coordinate of the enemy.
     * @return The x-coordinate.
     */
    public int getXCoordinate() {
        return this.xCoordinate;
    }

    /**
     * Gets the y-coordinate of the enemy.
     * @return The y-coordinate.
     */
    public int getYCoordinate() {
        return this.yCoordinate;
    }

    /**
     * Gets the level of the enemy.
     * @return The level.
     */
    public int getLevel() {
        return this.level;
    }
}

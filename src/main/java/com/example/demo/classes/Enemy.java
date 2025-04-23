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
     * The sprite of the enemy.
     */
    private final String sprite;

    /**
     * Constructs an Enemy object.
     * @param xCoordinate The x-coordinate of the enemy.
     * @param yCoordinate The y-coordinate of the enemy.
     * @param level The level of the enemy.
     * @param sprite The sprite of the enemy.
     */
    public Enemy(int xCoordinate, int yCoordinate, int level, String sprite) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.level = level;
        this.sprite = sprite;
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

    /**
     * Gets the sprite of the enemy.
     * @return The sprite.
     */
    public String getSprite() {
        return this.sprite;
    }
}

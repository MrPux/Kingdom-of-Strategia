package com.example.demo.classes;

/**
 * <h1>Enemy</h1>
 * <p>
 * Represents an enemy in the game.
 * This class encapsulates the properties of an enemy, such as its coordinates, level, and sprite.
 * </p>
 * <p>
 * The Enemy class is used to define the characteristics of an enemy character in the game.
 * It stores information about the enemy's position, strength, and appearance.
 * </p>
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
     * <h1>Enemy Constructor</h1>
     * <p>
     * Constructs an Enemy object.
     * </p>
     *
     * @param xCoordinate The x-coordinate of the enemy.
     * @param yCoordinate The y-coordinate of the enemy.
     * @param level       The level of the enemy.
     * @param sprite      The sprite of the enemy.
     */
    public Enemy(int xCoordinate, int yCoordinate, int level, String sprite) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.level = level;
        this.sprite = sprite;
    }

    /**
     * Gets the x-coordinate of the enemy.
     *
     * @return The x-coordinate.
     */
    public int getXCoordinate() {
        return this.xCoordinate;
    }

    /**
     * Gets the y-coordinate of the enemy.
     *
     * @return The y-coordinate.
     */
    public int getYCoordinate() {
        return this.yCoordinate;
    }

    /**
     * Gets the level of the enemy.
     *
     * @return The level.
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Gets the sprite of the enemy.
     *
     * @return The sprite.
     */
    public String getSprite() {
        return this.sprite;
    }
}

/**
 * Mountain Object, a geographical feature within the game world.
 */
package com.example.demo.classes;

/**
 * Represents a mountain in the game world.
 * This class encapsulates the properties of a mountain, such as its coordinates and sprite.
 */
public class Mountain {
    /**
     * The x-coordinate of the mountain.
     */
    private int xCoordinate;
    /**
     * The y-coordinate of the mountain.
     */
    private int yCoordinate;
    /**
     * The sprite of the mountain.
     */
    private String sprite;

    /**
     * Constructs a Mountain object. Takes the x and y coordinates as preconditionsto define its position in the game world.
     *
     * @param xCoordinate The x coordinate of the mountain.
     * @param yCoordinate The y coordinate of the mountain.
     * @param sprite      The sprite of the mountain.
     */
    public Mountain(int xCoordinate, int yCoordinate, String sprite) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.sprite = sprite;
    }

    /**
     * returns x coordinate.
     *
     * @return xCoordinate The horizontal coordinate of the mountain.
     */
    public int getXCoordinate() {
        return this.xCoordinate;
    }

    /**
     * returns y coordinate.
     *
     * @return yCoordinate The vertical coordinate of the mountain.
     */
    public int getYCoordinate() {
        return this.yCoordinate;
    }

    /**
     * Returns the assigned sprite png of the mountain.
     *
     * @return mountain
     */
    public String getSprite() {
        return this.sprite;
    }
}

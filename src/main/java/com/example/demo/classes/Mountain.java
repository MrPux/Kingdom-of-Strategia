/**
 * As a Mountain, I represent a geographical feature within the game world.
 */
package com.example.demo.classes;

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
     * am the constructor for creating a Mountain object. I take the x and y coordinates as input
     * to define my position in the game world.
     * @param xCoordinate The x coordinate of the mountain.
     * @param yCoordinate The y coordinate of the mountain.
     */
    public Mountain(int xCoordinate, int yCoordinate, String sprite) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.sprite = sprite;
    }

    /**
     * eturn my x coordinate.
     * @return The x coordinate of the mountain.
     */
    public int getXCoordinate() {
        return this.xCoordinate;
    }

    /**
     * return my y coordinate.
     * @return The y coordinate of the mountain.
     */
    public int getYCoordinate() {
        return this.yCoordinate;
    }

    /**
     * Returns the assigned sprite png of the mountain.
     * @return mountain
     */
    public String getSprite() {
        return this.sprite;
    }
}

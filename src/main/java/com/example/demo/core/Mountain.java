/**
 * As a Mountain, I represent a geographical feature within the game world.
 */
package com.example.demo.core;

public class Mountain
{
    private int xCoordinate;
    private int yCoordinate;

    /**
     * I am the constructor for creating a Mountain object. I take the x and y coordinates as input
     * to define my position in the game world.
     * @param xCoordinate The x coordinate of the mountain.
     * @param yCoordinate The y coordinate of the mountain.
     */
    public Mountain(int xCoordinate, int yCoordinate)
    {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    /**
     * I return my x coordinate.
     * @return The x coordinate of the mountain.
     */
    public int getXCoordinate(){return this.xCoordinate;}
    
    /**
     * I return my y coordinate.
     * @return The y coordinate of the mountain.
     */
    public int getYCoordinate(){return this.yCoordinate;}
}

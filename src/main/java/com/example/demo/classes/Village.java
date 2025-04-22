/**
 * This class represents a village in the game.
 */
package com.example.demo.classes;

public class Village {
    /**
     * The name of the village.
     */
    private String name;
    /**
     * The x-coordinate of the village.
     */
    private int xCoordinate;
    /**
     * The y-coordinate of the village.
     */
    private int yCoordinate;
    /**
     * The unique identifier of the village.
     */
    private int id;
    /**
     * The sprite or image representing the village.
     */
    private String sprite;

    // Constructor
    /**
     * Constructs the Village Object based on the following required variablees for initialization of Villagee instance variables.
     * @param name The name of the village.
     * @param id The unique identifier of the village.
     * @param xCoordinate The x-coordinate of the village.
     * @param yCoordinate The y-coordinate of the village.
     * @param sprite The sprite or image representing the village.
     */
    public Village(String name, int id, int xCoordinate, int yCoordinate, String sprite) {
        this.name = name;
        this.id = id;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.sprite = sprite;
    }

    // Getters
    /**
     * Returns the name of the village as a String.
     * @return String Village name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the sprite of the village as a String.
     * @return String Village sprite.
     */
    public String getSprite() {
        return this.sprite;
    }

    /**
     * Returns the id of the village
     * @return int village identifier
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns the x cordinate location of the village
     * @return int X Cordinate
     */
    public int getXCoordinate() {
        return this.xCoordinate;
    }

    /**
     * Returns the y cordinate location of the village
     * @return int y cordinate
     */
    public int getYCoordinate() {
        return this.yCoordinate;
    }

    // Setters

    /**Sets the village name to the given String
     * @param String used as village name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**Gives the village an id based on the given integer
     * @param int as village identifier
     */
    public void setId(int id) {
        this.id = id;
    }

    /**Sets the village x cordinate based on the given integer
     * @param int used for Village X Cordinate
     */
    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /**
     * Sets the village y cordinate based on the given integer
     * @param int used for Village Y cordinate
     */
    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Village village = (Village) obj;
        return id == village.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

}

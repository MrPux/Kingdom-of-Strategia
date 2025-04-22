/**
 * This class represents a village in the game.
 */
package com.example.demo.classes;

public class Village {
    // Instance Variables
    private String name;
    private int xCoordinate, yCoordinate;
    private int id;

    // Constructor
    /**
     * Constructs the Village Object based on the following required variablees for initialization of Villagee instance variables.
     * @param name
     * @param id
     * @param xCordinate  
     * @param yCordinate
     */
    public Village(String name, int id, int xCoordinate, int yCoordinate)
    {
        this.name = name;
        this.id = id;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    // Getters
    /**
     * Returns the name of the village as a String.
     * @return String Village name.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Returns the id of the village
     * @return int village identifier
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * Returns the x cordinate location of the village
     * @return int X Cordinate
     */
    public int getXCoordinate()
    {
        return this.xCoordinate;
    }

    /**
     * Returns the y cordinate location of the village
     * @return int y cordinate
     */
    public int getYCoordinate()
    {
        return this.yCoordinate;
    }

    // Setters

    /**Sets the village name to the given String 
     * @param String used as village name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**Gives the village an id based on the given integer
     * @param int as village identifier 
     */
    public void setId(int id)
    {
        this.id = id;
    }
    
    /**Sets the village x cordinate based on the given integer
     * @param int used for Village X Cordinate
     */
    public void setXCoordinate(int xCordinate)
    {
        this.xCoordinate = xCordinate;
    }

    /**
     * Sets the village y cordinate based on the given integer
     * @param int used for Village Y cordinate
     */
    public void setYCoordinate(int yCordinate)
    {
        this.yCoordinate = yCordinate;
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

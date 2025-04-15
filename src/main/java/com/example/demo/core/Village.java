package com.example.demo.core;

public class Village {
    //Instance Variables
    private String name;
    private int xCordinate, yCordinate;
    private int id;

    //Constructor
    /**
     * Constructs the Village Object based on the following required variablees for initialization of Villagee instance variables.
     * @param name
     * @param id
     * @param xCordinate
     * @param yCordinate
     */
    public Village(String name, int id, int xCordinate, int yCordinate)
    {
        this.name = name;
        this.id = id;
        this.xCordinate = xCordinate;
        this.yCordinate = yCordinate;
    }

    // ----- Getters  ------
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
    public int getXCordinate()
    {
        return this.xCordinate;
    }

    /**
     * Returns the y cordinate location of the village
     * @return int y cordinate
     */
    public int getYCordinate()
    {
        return this.yCordinate;
    }

    // ----- Setters  ------

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
    public void setXCordinate(int xCordinate)
    {
        this.xCordinate = xCordinate;
    }

    /**
     * Sets the village y cordinate based on the given integer
     * @param int used for Village Y cordinate
     */
    public void setYCordinate(int yCordinate)
    {
        this.yCordinate = yCordinate;
    }
}

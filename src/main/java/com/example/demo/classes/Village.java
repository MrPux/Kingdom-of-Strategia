/**
 * This class represents a village in the game.
 */
package com.example.demo.classes;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Village {
    /**
     * The name of the village.
     */
    private String name;
        /**
     * The unique identifier of the village.
     */
    private int id;

    private int population;

    private int structures;
    /**
     * The x-coordinate of the village.
     */
    private int xCoordinate;
    /**
     * The y-coordinate of the village.
     */
    private int yCoordinate;
    /**
     * The sprite or image representing the village.
     */
    private String sprite;
    
    private VillageType type;
    

    private int knights;
    private int laborers;
    private int wood;
    private int food;
    private int iron;
    private int coal;
    private int rocks; 
    
    // Include VillageType in your constructor
    public Village(String name, int id, int population, int structures, int x, int y, String sprite, VillageType type) {
        this.name = name;
        this.id = id;
        this.population = population;
        this.structures = structures;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.sprite = sprite;
        this.type = type;
    
        // Default or based on type
        assignResourcesByType(type);
    }
    
    private void assignResourcesByType(VillageType type) {
        switch (type) {
            case COMMON:
                this.knights = 2;
                this.laborers = 2;
                this.wood = 5;
                this.food = 5;
                this.iron = 2;
                this.coal = 2;
                this.rocks = 3; 
                break;
            case TIMBER:
                this.knights = 3;
                this.laborers = 4;
                this.wood = 20;
                this.food = 6;
                this.iron = 2;
                this.coal = 2;
                this.rocks = 2; 
                break;
            case ARMOR:
                this.knights = 15;
                this.laborers = 5;
                this.wood = 6;
                this.food = 5;
                this.iron = 10;
                this.coal = 8;
                this.rocks = 5; 
                break;
            case COMPOSITE:
                this.knights = 12;
                this.laborers = 8;
                this.wood = 15;
                this.food = 10;
                this.iron = 10;
                this.coal = 10;
                this.rocks = 10; 
                break;
        }
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
    public int getPopulation() {
        return population;
    }
    
    public int getStructures() {
        return structures;
    }

    public int getKnights() {
        return knights;
    }

    public int getLaborers() {
        return laborers;
    }

    public int getWood() {
        return wood;
    }

    public int getFood() {
        return food;
    }

    public int getIron() {
        return iron;
    }

    public int getCoal() {
        return coal;
    }

    public int getRocks() {
        return rocks;
    }
 
    
    public String getFormattedName() {
        String base = sprite.substring(sprite.lastIndexOf("/") + 1).replace(".png", "");
        return Arrays.stream(base.split("[-_]"))
                    .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                    .collect(Collectors.joining(" "));
    }




public VillageType getType() {
    return type;
}

public void setType(VillageType type) {
    this.type = type;
}

    /**
     * Overrides the equals method to compare two Village objects.
     * This method checks if two Village objects are equal based on their id.
     * @param obj The object to compare with.
     * @return true if the villages are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        // If the object is the same instance, return true
        if (this == obj) return true;
        // If the object is null or not an instance of Village, return false
        if (obj == null || getClass() != obj.getClass()) return false;
        // Cast the object to Village
        Village village = (Village) obj;
        // Check if the villages are equal based on their id
        return id == village.id;
    }

    /**
     * Overrides the hashCode method to generate a hash code for the Village object.
     * The hash code is based on the id of the village.
     * @return The hash code for the Village object.
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

}

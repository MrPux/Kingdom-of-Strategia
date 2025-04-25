/**
 * This class represents a village in the game.
 */
package com.example.demo.classes;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Random;

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
    private int villagers;
    private int wood;
    private int food;
    private int iron;
    private int coal;
    private int rocks; 
    
    // Include VillageType in your constructor
    public Village(String name, int id, int structures, int x, int y, String sprite, VillageType type) {
        this.name = name;
        this.id = id; 
        this.structures = structures;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.sprite = sprite;
        this.type = type;
    
        // Default or based on type
        assignResourcesByType(type);
        this.population = this.villagers + this.knights;
    }
    
    private void assignResourcesByType(VillageType type) {
        Random rand = new Random();
    
        switch (type) {
            case COMMON:
                this.knights = rand.nextInt(4) + 4;       // 4–7
                this.villagers = rand.nextInt(5) + 5;     // 5–9
                this.wood = rand.nextInt(5) + 6;          // 6–10
                this.food = rand.nextInt(4) + 6;          // 6–9
                this.iron = rand.nextInt(3) + 4;          // 4–6
                this.coal = rand.nextInt(2) + 4;          // 4–5
                this.rocks = rand.nextInt(4) + 4;         // 4–7
                break;

            case TIMBER:
                this.knights = rand.nextInt(3) + 4;       // 4–6 — not meant for fighting
                this.villagers = rand.nextInt(4) + 6;     // 6–9 — good workforce
                this.wood = rand.nextInt(15) + 15;        // 15–29 — lots of wood!
                this.food = rand.nextInt(4) + 6;          // 6–9 — moderate
                this.iron = rand.nextInt(2) + 4;          // 4–5 — low
                this.coal = rand.nextInt(2) + 4;          // 4–5 — low
                this.rocks = rand.nextInt(3) + 4;         // 4–6 — moderate
                break;
    
                case ARMOR:
                    this.knights = rand.nextInt(8) + 18;      // 18–25 — strong military
                    this.villagers = rand.nextInt(4) + 6;     // 6–9 — support staff
                    this.wood = rand.nextInt(3) + 4;          // 4–6 — not many trees
                    this.food = rand.nextInt(3) + 4;          // 4–6 — must import food
                    this.iron = rand.nextInt(6) + 10;         // 10–15 — weapon grade
                    this.coal = rand.nextInt(5) + 8;          // 8–12 — for forging
                    this.rocks = rand.nextInt(4) + 6;         // 6–9 — for walls/forts
                    break;
    
            case COMPOSITE:
                this.knights = rand.nextInt(9) + 20;     // 20–28
                this.villagers = rand.nextInt(9) + 20;
                this.wood = rand.nextInt(9) + 20;
                this.food = rand.nextInt(9) + 20;
                this.iron = rand.nextInt(9) + 20;
                this.coal = rand.nextInt(9) + 20;
                this.rocks = rand.nextInt(9) + 20;
                break;
        }
    
        this.population = this.knights + this.villagers;
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

    public int getVillagers() {
        return this.villagers;
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

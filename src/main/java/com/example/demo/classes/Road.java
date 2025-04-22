/**
 * This class represents a road in the game.
 */
package com.example.demo.classes;

public class Road {
    // Instance Variables
    private Village fromVillage;
    private Village toVillage;
    private int weight;

    // Constructor
    /**
     * The Construction of this Road object requires the following variables for initalazing its instance variables.
     * @param from
     * @param to
     * @param weight
     */
    public Road(Village fromVillage, Village toVillage, int weight)
    {
        this.fromVillage = fromVillage;
        this.toVillage = toVillage;
        this.weight = weight;
    }

    // Getters
    /**
     * Returns the Village where the road comes from.
     * @return Village 
     */
    public Village getFromVillage()
    {
        return this.fromVillage;
    }

    /**
     * Returns the Village where the road heads towards.
     * @return toVillage
     */
    public Village getToVillage()
    {
        return this.toVillage;
    }

    /**
     * Returns the weight of the road that are very important when traversing.
     * @return weight
     */
    public int getWeight()
    {
        return this.weight;
    }

   
    @Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Road other = (Road) obj;
    return (fromVillage.equals(other.fromVillage) && toVillage.equals(other.toVillage)) ||
           (fromVillage.equals(other.toVillage) && toVillage.equals(other.fromVillage)); // undirected
}

@Override
public int hashCode() {
    return fromVillage.hashCode() + toVillage.hashCode(); // order doesn't matter
}

}

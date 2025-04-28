/**
 * This class represents a road in the game.
 */
package com.example.demo.classes;

import com.example.demo.classes.villageClasses.Village;

/**
 * <h1>Road</h1>
 * <p>
 * Represents a road connecting two villages in the game world.
 * This class encapsulates the properties of a road, such as the villages it connects, its weight, and its type.
 * </p>
 * <p>
 * The Road class is used to define the characteristics of a road connecting two villages in the game world.
 * It stores information about the villages it connects, the cost of traversing the road, and the type of road.
 * </p>
 */
public class Road {
    /**
     * The village where the road originates.
     */
    private Village fromVillage;
    /**
     * The village where the road leads to.
     */
    private Village toVillage;
    /**
     * The weight or cost of traversing the road.
     */
    private int weight;

    private RoadType type; // NEW FIELD


    // Constructors

    /**
     * <h1>Road Constructor</h1>
     * <p>
     * Constructs a Road object with a default road type.
     * </p>
     *
     * @param from The village where the road originates.
     * @param to   The village where the road leads to.
     * @param weight The weight or cost of traversing the road.
     */
    public Road(Village from, Village to, int weight) {
        this(from, to, weight, RoadType.NONE);
    }

    /**
     * <h1>Road Constructor</h1>
     * <p>
     * The Construction of this Road object requires the following variables for initalazing its instance variables.
     * </p>
     *
     * @param fromVillage The village where the road originates.
     * @param toVillage   The village where the road leads to.
     * @param weight      The weight or cost of traversing the road.
     */
    public Road(Village fromVillage, Village toVillage, int weight, RoadType type) {
        this.fromVillage = fromVillage;
        this.toVillage = toVillage;
        this.weight = weight;
        this.type = type;
    }

    // Getters

    /**
     * Returns the Village where the road comes from.
     *
     * @return Village
     */
    public Village getFromVillage() {
        return this.fromVillage;
    }

    /**
     * Returns the Village where the road heads towards.
     *
     * @return toVillage
     */
    public Village getToVillage() {
        return this.toVillage;
    }

    /**
     * Returns the weight of the road that are very important when traversing.
     *
     * @return weight
     */
    public int getWeight() {
        return this.weight;
    }

    /**
     * Returns the type of the road.
     *
     * @return type
     */
    public RoadType getType() {
        return type;
    }


    /**
     * <h1>equals Method</h1>
     * <p>
     * Overrides the equals method to compare two Road objects.
     * This method checks if two Road objects are equal based on their fromVillage and toVillage.
     * Since the road is undirected, the order of villages doesn't matter.
     * </p>
     *
     * @param obj The object to compare with.
     * @return true if the roads are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        // If the object is the same instance, return true
        if (this == obj) return true;
        // If the object is null or not an instance of Road, return false
        if (obj == null || getClass() != obj.getClass()) return false;
        // Cast the object to Road
        Road other = (Road) obj;
        // Check if the roads are equal in either direction (undirected)
        return (fromVillage.equals(other.fromVillage) && toVillage.equals(other.toVillage)) ||
                (fromVillage.equals(other.toVillage) && toVillage.equals(other.fromVillage)); // undirected
    }

    /**
     * <h1>hashCode Method</h1>
     * <p>
     * Overrides the hashCode method to generate a hash code for the Road object.
     * The hash code is based on the hash codes of the fromVillage and toVillage.
     * The order of villages doesn't matter, so the hash codes are simply added together.
     * </p>
     *
     * @return The hash code for the Road object.
     */
    @Override
    public int hashCode() {
        return fromVillage.hashCode() + toVillage.hashCode(); // order doesn't matter
    }

}

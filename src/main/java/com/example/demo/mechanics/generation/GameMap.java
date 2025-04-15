package com.example.demo.mechanics.generation;
import com.example.demo.core.Road;
import com.example.demo.core.Village;
import com.example.demo.utils.LinkedList;

public class GameMap {
    
    //Instance Variables
    private LinkedList<Village> villages;
    private LinkedList<Road> roads;

    //Constructor
    /**
     * Initializes the instance variables of this MapGenerator Object
     */
    public GameMap()
    {
        this.villages = new LinkedList();
        this.roads = new LinkedList();
    }

    //--------- Getters --------
    /**
     * Returns a LinkedList of villages from the Map.
     * @return LinkedList<Village>
     */
    public LinkedList<Village> getVillages()
    {
        return this.villages;
    }

    /**
     * Returns a Linked List of Roads from the Map.
     * @return LinkedList<Road>
     */
    public LinkedList<Road> getRoads()
    {
        return this.roads;
    }

    //-------- Setters ------------
    /**
     * Adds a village Object to our linkedList Structure.
     * @param Village
     */
    public void addVillage(Village newVillage)
    {
        this.villages.add(newVillage);
    }

    /**
     * Adds a Road Object to a LinkedList Structure.
     * @param road
     */
    public void addRoad(Road newRoad)
    {
        this.roads.add(newRoad);
    }

    /**
     * Clears the map off Villages and Roads.
     */
    public void clearMap()
    {
        villages.clear();
        roads.clear();
    }

    
}

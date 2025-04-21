/**
 * This class represents the game map.
 */
package com.example.demo.mechanics.generation;
import com.example.demo.core.Mountain;
import com.example.demo.core.Road;
import com.example.demo.core.Village;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    
    //Instance Variables
    private List<Village> villages;
    private List<Road> roads;
    private List<Mountain> mountains;

    //Constructor
    /**
     * Initializes the instance variables of this MapGenerator Object
     */
    public GameMap()
    {
        this.villages = new ArrayList<>();
        this.roads = new ArrayList<>();
        this.mountains = new ArrayList<>();
    }

    //--------- Getters --------
    /**
     * Returns a List of villages from the Map.
     * @return List<Village>
     */
    public List<Village> getVillages()
    {
        return this.villages;
    }

    /**
     * Returns a List of Roads from the Map.
     * @return List<Road>
     */
    public List<Road> getRoads()
    {
        return this.roads;
    }

    /**
     *Returns a List of Mounatians from Map.
     * @return mountains
     */
    public List<Mountain> getMountains(){return this.mountains;}

    //-------- Setters ------------
    /**
     * Adds a village Object to our List Structure.
     * @param Village
     */
    public void addVillage(Village newVillage)
    {
        this.villages.add(newVillage);
    }

    /**
     * Adds a Road Object to a List Structure.
     * @param road
     */
    public void addRoad(Road newRoad)
    {
        this.roads.add(newRoad);
    }

    /**
     * Adds Mountain Objects to List 
     */
    public void addMountain(Mountain m)
    {
        this.mountains.add(m);
    }


    /**
     * Clears the map off Villages and Roads.
     */
    public void clearMap()
    {
        villages.clear();
        roads.clear();
        mountains.clear();
    }


}

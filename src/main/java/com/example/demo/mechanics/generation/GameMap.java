/**
 * This class represents the game map.
 */
package com.example.demo.mechanics.generation;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.classes.Mountain;
import com.example.demo.classes.Road;
import com.example.demo.classes.Village;
import com.example.demo.classes.Enemy;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.classes.Mountain;
import com.example.demo.classes.Road;
import com.example.demo.classes.Village;
import com.example.demo.classes.Enemy;

/**
 * This class represents the game map.
 */
public class GameMap {

    /**
     * A list of mountains on the map.
     */
    private List<Mountain> mountains;
    /**
     * A list of villages on the map.
     */
    private List<Village> villages;
    /**
     * A list of roads on the map.
     */
    private List<Road> roads;
    /**
     * A list of enemies on the map.
     */
    private List<Enemy> enemies;

    //Constructor
    /**
     * Initializes the instance variables of this MapGenerator Object
     */
    public GameMap() {
        this.villages = new ArrayList<>();
        this.roads = new ArrayList<>();
        this.mountains = new ArrayList<>();
        this.enemies = new ArrayList<>();
    }

    //--------- Getters --------

    /**
     * Returns a List of Mounatians from Map.
     * @return mountains
     */
    public List<Mountain> getMountains() {
        return this.mountains;
    }

    /**
     * Returns a List of villages from the Map.
     * @return List<Village>
     */
    public List<Village> getVillages() {
        return this.villages;
    }

    /**
     * Returns a List of Roads from the Map.
     * @return List<Road>
     */
    public List<Road> getRoads() {
        return this.roads;
    }

    /**
     * Returns a List of enemies on map
     * @param enemies
     */
    public List<Enemy> getEnemies() {
        return this.enemies;
    }

    //-------- Setters ------------
    /**
     * Adds a village Object to our List Structure.
     * @param Village
     */
    public void addVillage(Village newVillage) {
        this.villages.add(newVillage);
    }

    /**
     * Adds a Road Object to a List Structure.
     * @param road
     */
    public void addRoad(Road newRoad) {
        this.roads.add(newRoad);
    }

    /**
     * Adds Mountain Objects to mountains List Structure.
     */
    public void addMountain(Mountain newMountain) {
        this.mountains.add(newMountain);
    }

    /**
     * Adds Enemy Objects to the enemies List Structure.
     */
    public void addEnemy(Enemy newEnemy) {
        this.enemies.add(newEnemy);
    }

    /**
     * Clears the map off Villages and Roads.
     */
    public void clearMap() {
        villages.clear();
        roads.clear();
        mountains.clear();
        enemies.clear();
    }


}

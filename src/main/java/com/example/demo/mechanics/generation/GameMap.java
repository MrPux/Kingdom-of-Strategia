package com.example.demo.mechanics.generation;

import com.example.demo.classes.Enemy;
import com.example.demo.classes.Mountain;
import com.example.demo.classes.Road;
import com.example.demo.classes.villageClasses.Village;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Represents the game map, containing mountains, villages, roads, and enemies.
 */
@Component
public class GameMap {

    private List<Mountain> mountains;
    private List<Village> villages;
    private List<Road> roads;
    private List<Enemy> enemies;
    private Village startingVillage;

    /**
     * Constructs a new GameMap object.
     * Initializes the lists of mountains, villages, roads, and enemies.
     */
    public GameMap() {
        this.villages = new ArrayList<>();
        this.roads = new ArrayList<>();
        this.mountains = new ArrayList<>();
        this.enemies = new ArrayList<>();
    }

    /**
     * Gets the list of mountains on the map.
     *
     * @return The list of mountains.
     */
    public List<Mountain> getMountains() {
        return this.mountains;
    }

    /**
     * Gets the list of villages on the map.
     *
     * @return The list of villages.
     */
    public List<Village> getVillages() {
        return this.villages;
    }

    /**
     * Gets the list of roads on the map.
     *
     * @return The list of roads.
     */
    public List<Road> getRoads() {
        return this.roads;
    }

    /**
     * Gets the list of enemies on the map.
     *
     * @return The list of enemies.
     */
    public List<Enemy> getEnemies() {
        return this.enemies;
    }

    /**
     * Gets the starting village on the map.
     *
     * @return The starting village.
     */
    public Village getStartingVillage() {
        return this.startingVillage;
    }

    /**
     * Sets the list of villages on the map.
     *
     * @param villages The new list of villages.
     */
    public void setVillages(List<Village> villages) {
        this.villages = villages;
    }

    /**
     * Sets the list of mountains on the map.
     *
     * @param mountains The new list of mountains.
     */
    public void setMountains(List<Mountain> mountains) {
        this.mountains = mountains;
    }

    /**
     * Sets the list of roads on the map.
     *
     * @param roads The new list of roads.
     */
    public void setRoads(List<Road> roads) {
        this.roads = roads;
    }

    /**
     * Sets the list of enemies on the map.
     *
     * @param enemies The new list of enemies.
     */
    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    /**
     * Sets the starting village on the map.
     *
     * @param startingVillage The new starting village.
     */
    public void setStartingVillage(Village startingVillage) {
        this.startingVillage = startingVillage;
    }

    /**
     * Adds a new village to the map.
     *
     * @param newVillage The village to add.
     */
    public void addVillage(Village newVillage) {
        this.villages.add(newVillage);
    }

    /**
     * Adds a new road to the map.
     *
     * @param newRoad The road to add.
     */
    public void addRoad(Road newRoad) {
        this.roads.add(newRoad);
    }

    /**
     * Adds a new mountain to the map.
     *
     * @param newMountain The mountain to add.
     */
    public void addMountain(Mountain newMountain) {
        this.mountains.add(newMountain);
    }

    /**
     * Adds a new enemy to the map.
     *
     * @param newEnemy The enemy to add.
     */
    public void addEnemy(Enemy newEnemy) {
        this.enemies.add(newEnemy);
    }

    /**
     * Clears all the elements on the map.
     */
    public void clearMap() {
        villages.clear();
        roads.clear();
        mountains.clear();
        enemies.clear();
        startingVillage = null;
    } 
}

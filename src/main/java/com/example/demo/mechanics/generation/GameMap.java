package com.example.demo.mechanics.generation;

import com.example.demo.classes.Enemy;
import com.example.demo.classes.Mountain;
import com.example.demo.classes.Road;
import com.example.demo.classes.Village;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class GameMap {

    private List<Mountain> mountains;
    private List<Village> villages;
    private List<Road> roads;
    private List<Enemy> enemies;
    private Village startingVillage;

    public GameMap() {
        this.villages = new ArrayList<>();
        this.roads = new ArrayList<>();
        this.mountains = new ArrayList<>();
        this.enemies = new ArrayList<>();
    }

    public List<Mountain> getMountains() {
        return this.mountains;
    }

    public List<Village> getVillages() {
        return this.villages;
    }

    public List<Road> getRoads() {
        return this.roads;
    }

    public List<Enemy> getEnemies() {
        return this.enemies;
    }

    public Village getStartingVillage() {
        return this.startingVillage;
    }

    public void setVillages(List<Village> villages) {
        this.villages = villages;
    }

    public void setMountains(List<Mountain> mountains) {
        this.mountains = mountains;
    }

    public void setRoads(List<Road> roads) {
        this.roads = roads;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public void setStartingVillage(Village startingVillage) {
        this.startingVillage = startingVillage;
    }

    public void addVillage(Village newVillage) {
        this.villages.add(newVillage);
    }

    public void addRoad(Road newRoad) {
        this.roads.add(newRoad);
    }

    public void addMountain(Mountain newMountain) {
        this.mountains.add(newMountain);
    }

    public void addEnemy(Enemy newEnemy) {
        this.enemies.add(newEnemy);
    }

    public void clearMap() {
        villages.clear();
        roads.clear();
        mountains.clear();
        enemies.clear();
        startingVillage = null;
    } 
}

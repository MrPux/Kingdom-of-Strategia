package com.example.demo.classes;
import com.example.demo.classes.villageClasses.Village;

public class ResourcesStorage {

    private int totalFoodCollected = 0;
    private int totalWoodCollected = 0;
    private int totalRocksCollected = 0;
    private int totalIronCollected = 0;
    private int totalCoalCollected = 0;
    private int totalVillagersCollected = 0;
    private int totalKnightsCollected = 0;

    // Constructor
    public ResourcesStorage() {
    }

    // Add resources from one village
    public void addFromVillage(Village village) {
        totalFoodCollected += village.getFood();
        totalWoodCollected += village.getWood();
        totalRocksCollected += village.getRocks();
        totalIronCollected += village.getIron();
        totalCoalCollected += village.getCoal();
        totalVillagersCollected += village.getVillagers();
        totalKnightsCollected += village.getKnights();
    }

    // Getters
    public int getTotalFoodCollected() {
        return totalFoodCollected;
    }

    public int getTotalWoodCollected() {
        return totalWoodCollected;
    }

    public int getTotalRocksCollected() {
        return totalRocksCollected;
    }

    public int getTotalIronCollected() {
        return totalIronCollected;
    }

    public int getTotalCoalCollected() {
        return totalCoalCollected;
    }

    public int getTotalVillagersCollected() {
        return totalVillagersCollected;
    }

    public int getTotalKnightsCollected() {
        return totalKnightsCollected;
    }
}

package com.example.demo.classes;
import com.example.demo.classes.villageClasses.Village;

/**
 * <h1>ResourcesStorage</h1>
 * <p>
 * This class is responsible for storing the total resources collected by the player.
 * It includes methods to add resources from a village and to get the total amount of each resource.
 * </p>
 */
public class ResourcesStorage {

    /**
     * Total food collected.
     */
    private int totalFoodCollected = 0;
    /**
     * Total wood collected.
     */
    private int totalWoodCollected = 0;
    /**
     * Total rocks collected.
     */
    private int totalRocksCollected = 0;
    /**
     * Total iron collected.
     */
    private int totalIronCollected = 0;
    /**
     * Total coal collected.
     */
    private int totalCoalCollected = 0;
    /**
     * Total villagers collected.
     */
    private int totalVillagersCollected = 0;
    /**
     * Total knights collected.
     */
    private int totalKnightsCollected = 0;

    /**
     * <h1>ResourcesStorage Constructor</h1>
     * <p>
     * Constructor for the ResourcesStorage class.
     * </p>
     */
    public ResourcesStorage() {
    }

    /**
     * <h1>addFromVillage Method</h1>
     * <p>
     * Adds resources from one village to the total resources collected.
     * </p>
     *
     * @param village The village to add resources from.
     */
    public void addFromVillage(Village village) {
        // ========================= Add resources from the village =========================
        totalFoodCollected += village.getFood();
        totalWoodCollected += village.getWood();
        totalRocksCollected += village.getRocks();
        totalIronCollected += village.getIron();
        totalCoalCollected += village.getCoal();
        totalVillagersCollected += village.getVillagers();
        totalKnightsCollected += village.getKnights();
    }

    /**
     * <h1>getTotalFoodCollected Method</h1>
     * <p>
     * Gets the total food collected.
     * </p>
     *
     * @return The total food collected.
     */
    public int getTotalFoodCollected() {
        return totalFoodCollected;
    }

    /**
     * <h1>getTotalWoodCollected Method</h1>
     * <p>
     * Gets the total wood collected.
     * </p>
     *
     * @return The total wood collected.
     */
    public int getTotalWoodCollected() {
        return totalWoodCollected;
    }

    /**
     * <h1>getTotalRocksCollected Method</h1>
     * <p>
     * Gets the total rocks collected.
     * </p>
     *
     * @return The total rocks collected.
     */
    public int getTotalRocksCollected() {
        return totalRocksCollected;
    }

    /**
     * <h1>getTotalIronCollected Method</h1>
     * <p>
     * Gets the total iron collected.
     * </p>
     *
     * @return The total iron collected.
     */
    public int getTotalIronCollected() {
        return totalIronCollected;
    }

    /**
     * <h1>getTotalCoalCollected Method</h1>
     * <p>
     * Gets the total coal collected.
     * </p>
     *
     * @return The total coal collected.
     */
    public int getTotalCoalCollected() {
        return totalCoalCollected;
    }

    /**
     * <h1>getTotalVillagersCollected Method</h1>
     * <p>
     * Gets the total villagers collected.
     * </p>
     *
     * @return The total villagers collected.
     */
    public int getTotalVillagersCollected() {
        return totalVillagersCollected;
    }

    /**
     * <h1>getTotalKnightsCollected Method</h1>
     * <p>
     * Gets the total knights collected.
     * </p>
     *
     * @return The total knights collected.
     */
    public int getTotalKnightsCollected() {
        return totalKnightsCollected;
    }
}

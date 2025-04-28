package com.example.demo.classes.villageClasses;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>StructureNode</h1>
 * <p>
 * Represents a structure node within a village.
 * Each structure node has an ID, coordinates, a sprite for visual representation,
 * and a list of connections to other structure nodes via structure roads.
 * </p>
 * <p>
 * The StructureNode class is a fundamental building block of the village structure graph.
 * It represents a building or landmark within the village and stores information about its
 * location, appearance, and connections to other structures.
 * </p>
 */
public class StructureNode {
    private int id;
    private int xCoordinate;
    private int yCoordinate;
    private String sprite;
    private List<StructureRoad> connections = new ArrayList<>();

    /**
     * <h1>StructureNode Constructor</h1>
     * <p>
     * Constructs a new StructureNode object.
     * </p>
     *
     * @param id          The ID of the structure node.
     * @param xCoordinate The x-coordinate of the structure node within the village.
     * @param yCoordinate The y-coordinate of the structure node within the village.
     * @param sprite      The sprite representing the structure node.
     */
    public StructureNode(int id, int xCoordinate, int yCoordinate, String sprite) {
        this.id = id;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.sprite = sprite;
    }

    /**
     * <h1>connectTo Method</h1>
     * <p>
     * Connects this structure node to another structure node with a given weight.
     * Creates a new StructureRoad object representing the connection and adds it to the list of connections.
     * </p>
     * <p>
     * This method establishes a connection between two structure nodes, creating a new StructureRoad
     * object to represent the connection. The weight of the connection can represent various factors,
     * such as distance or cost.
     * </p>
     *
     * @param toStructure The structure node to connect to.
     * @param weight      The weight of the connection (e.g., distance, cost).
     */
    public void connectTo(StructureNode toStructure, int weight) {
        connections.add(new StructureRoad(this, toStructure, weight));
    }

    /**
     * Gets the ID of the structure node.
     *
     * @return The ID of the structure node.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the structure node.
     *
     * @param id The new ID of the structure node.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the x-coordinate of the structure node.
     *
     * @return The x-coordinate of the structure node.
     */
    public int getXCoordinate() {
        return xCoordinate;
    }

    /**
     * Sets the x-coordinate of the structure node.
     *
     * @param xCoordinate The new x-coordinate of the structure node.
     */
    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /**
     * Gets the y-coordinate of the structure node.
     *
     * @return The y-coordinate of the structure node.
     */
    public int getYCoordinate() {
        return yCoordinate;
    }

    /**
     * Sets the y-coordinate of the structure node.
     *
     * @param yCoordinate The new y-coordinate of the structure node.
     */
    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /**
     * Gets the sprite of the structure node.
     *
     * @return The sprite of the structure node.
     */
    public String getSprite() {
        return sprite;
    }

    /**
     * Sets the sprite of the structure node.
     *
     * @param sprite The new sprite of the structure node.
     */
    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    /**
     * Gets the list of connections (structure roads) from this structure node.
     *
     * @return The list of connections from this structure node.
     */
    public List<StructureRoad> getConnections() {
        return connections;
    }

    /**
     * Sets the list of connections (structure roads) from this structure node.
     *
     * @param connections The new list of connections from this structure node.
     */
    public void setConnections(List<StructureRoad> connections) {
        this.connections = connections;
    }
}

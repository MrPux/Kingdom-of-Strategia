/**
 * The main application class, responsible for initiating the Spring Boot application.
 */
package com.example.demo;

import com.example.demo.classes.villageClasses.StructureNode;
import com.example.demo.classes.villageClasses.Village;
import com.example.demo.classes.villageClasses.VillageType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import jakarta.annotation.PostConstruct;

/**
 * This is the main class for the GameApplication.
 * It uses Spring Boot to bootstrap and launch the application.
 */
@SpringBootApplication
public class GameApplication {

	/**
	 * This is where I start the application. I call the SpringApplication.run method,
	 * which in turn bootstraps and launches the entire Spring Boot application.
	 * @param args Command line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(GameApplication.class, args);
	}

    /**
     * This method is executed after the application context is initialized.
     * It creates a sample village, adds structures, connects them, and checks for a negative cycle.
     * This is used for testing the negative cycle detection functionality.
     */
    @PostConstruct
    public void testNegativeCycleDetection() {
        // Create a new village
        Village village = new Village("Test Village", 1, 3, 0, 0, "commonVillage.png", VillageType.COMMON);
    
        // Create structure nodes
        StructureNode structure1 = new StructureNode(0, 100, 100, "/assets/structures/CommonVIllage-Houses/commonVillageHouse1.png");
        StructureNode structure2 = new StructureNode(1, 200, 100, "/assets/structures/house2.png");
        StructureNode structure3 = new StructureNode(2, 150, 200, "/assets/structures/house3.png");
    
        // Add structures to the village
        village.addStructureNode(structure1);
        village.addStructureNode(structure2);
        village.addStructureNode(structure3);
    
        // Add connections (edges) between structures with weights
        structure1.connectTo(structure2, 5);
        structure2.connectTo(structure3, 2);
        structure3.connectTo(structure1, -10);
    
        // Check if there is a negative cycle
        boolean hasNegativeCycle = village.hasNegativeCycle();
    
        // Output the result
        System.out.println("Does the village structure have a negative cycle? " + hasNegativeCycle);
    }
    
}

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
 * <h1>GameApplication</h1>
 * <p>
 * This is the main class for the GameApplication.
 * It uses Spring Boot to bootstrap and launch the application.
 * </p>
 * <p>
 * The {@code @SpringBootApplication} annotation is a convenience annotation that adds all of the following:
 * <ul>
 *   <li>{@code @Configuration}: Tags the class as a source of bean definitions for the application context.</li>
 *   <li>{@code @EnableAutoConfiguration}: Tells Spring Boot to start adding beans based on classpath settings,
 *   other beans, and various property settings.</li>
 *   <li>{@code @ComponentScan}: Tells Spring to look for other components, configurations, and services in the
 *   {@code com.example.demo} package, allowing it to find the controllers.</li>
 * </ul>
 * </p>
 */
@SpringBootApplication
public class GameApplication {

	/**
	 * <h1>main Method</h1>
	 * <p>
	 * This is the entry point of the application.
	 * It calls the {@code SpringApplication.run} method, which in turn bootstraps and launches the entire Spring Boot application.
	 * </p>
	 *
	 * @param args Command line arguments passed to the application.
	 */
	public static void main(String[] args) {
		// Launch the Spring Boot application
		SpringApplication.run(GameApplication.class, args);
	}

    /**
     * <h1>testNegativeCycleDetection Method</h1>
     * <p>
     * This method is executed after the application context is initialized.
     * It creates a sample village, adds structures, connects them, and checks for a negative cycle.
     * This is used for testing the negative cycle detection functionality.
     * </p>
     * <p>
     * The {@code @PostConstruct} annotation ensures that this method is executed only once after the
     * {@link GameApplication} bean has been fully constructed and all dependencies have been injected.
     * </p>
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

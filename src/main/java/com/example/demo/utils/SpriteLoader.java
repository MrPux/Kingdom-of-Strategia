package com.example.demo.utils;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections; // Import Collections
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

/**
 * A utility class responsible for loading sprite filenames from the classpath.
 * This class provides methods to load both village and mountain sprite filenames,
 * and handles potential IOExceptions during the loading process.
 */
public class SpriteLoader {

    /**
     * Logger for this class, used for logging messages and debugging.
     */
    private static final Logger logger = LoggerFactory.getLogger(SpriteLoader.class);

    /**
     * A list of village sprite filenames. 
     * This list is populated during class initialization by the {@link #loadVillageSprites()} method.
     */
    public static final List<String> VILLAGE_SPRITES;

    /**
     * A list of mountain sprite filenames. 
     * This list is populated during class initialization by the {@link #loadMountainSprites()} method.
     */
    public static final List<String> MOUNTAIN_SPRITES;

    /**
     * Static initializer block that is executed when the class is loaded.
     * This block calls the methods to load the village and mountain sprite lists.
     */
    static {
        // Load village sprites by calling the loadVillageSprites() method
        VILLAGE_SPRITES = loadVillageSprites();
        // Load mountain sprites by calling the loadMountainSprites() method
        MOUNTAIN_SPRITES = loadMountainSprites();
    }

    /**
     * Loads the village sprite filenames from the classpath.
     * This method uses a {@link ResourcePatternResolver} to find all PNG files in the
     * "classpath:/static/assets/villages/" directory and adds them to a list.
     * It also ensures that "commonVillage.png" is present and adds it multiple times
     * to increase its probability of being selected.
     * @return A list of village sprite filenames.
     */
    private static List<String> loadVillageSprites() {
        // ResourcePatternResolver to find resources in the classpath
        final ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        // List to store the filenames
        final List<String> filenames = new ArrayList<>();
        try {
            // Get all resources matching the pattern "classpath:/static/assets/villages/*.png"
            final Resource[] resources = resourcePatternResolver.getResources("classpath:/static/assets/villages/*.png");
            // Convert the resources to a list of filenames
            resourcesToList(resources, filenames);

            // Ensure "commonVillage.png" is present, then add it multiple times to increase its probability
            if (filenames.contains("commonVillage.png")) {
                // Number of times to add "commonVillage.png"
                final int commonVillageCount = 3;
                // Add "commonVillage.png" multiple times to increase its probability
                for (int i = 0; i < commonVillageCount; i++) {
                    filenames.add("commonVillage.png");
                }
                // Shuffle the list to randomize the order of the sprites
                Collections.shuffle(filenames);
            } else {
                // Log a warning if "commonVillage.png" is not found in the directory
                logger.warn("commonVillage.png not found in the village sprites directory!");
            }

        } catch (IOException e) {
            // Log an error if there was an issue loading the village sprites
            logger.error("Error loading village sprites", e);
            // Consider rethrowing as a RuntimeException if the application cannot function without these sprites
        }
        // Return the list of filenames
        return filenames;
    }

    /**
     * Loads the mountain sprite filenames from the classpath.
     * This method uses a {@link ResourcePatternResolver} to find all PNG files in the
     * "classpath:/static/assets/mountains/" directory and adds them to a list.
     * @return A list of mountain sprite filenames.
     */
    private static List<String> loadMountainSprites() {
        // ResourcePatternResolver to find resources in the classpath
        final ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        // List to store the filenames
        List<String> filenames = new ArrayList<>();
        try {
            // Get all resources matching the pattern "classpath:/static/assets/mountains/*.png"
            Resource[] resources = resourcePatternResolver.getResources("classpath:/static/assets/mountains/*.png");
            // Convert the resources to a list of filenames
            resourcesToList(resources, filenames);
            // Shuffle the list to randomize the order of the sprites
            Collections.shuffle(filenames);

        } catch (IOException e) {
            // Log an error if there was an issue loading the mountain sprites
            logger.error("Error loading mountain sprites", e);
            // Consider rethrowing as a RuntimeException if the application cannot function without these sprites
        }
        // Return the list of filenames
        return filenames;
    }

    /**
     * Converts an array of resources to a list of filenames.
     * This method iterates over the resources and extracts the filename from each resource.
     * @param resources The array of resources to convert.
     * @param filenames The list to store the filenames.
     * @throws IOException If there was an issue getting the filename from a resource.
     */
    private static void resourcesToList(Resource[] resources, List<String> filenames) throws IOException {
        // Iterate over the resources
        for (Resource resource : resources) {
            // Get the filename from the resource
            String filename = resource.getFilename();
            // Log the loaded mountain sprite
            logger.info("Loaded sprite: {}", filename);
            // Add the filename to the list
            filenames.add(filename);
        }
    }
}

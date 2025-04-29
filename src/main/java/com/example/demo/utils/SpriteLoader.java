package com.example.demo.utils;

import com.example.demo.classes.villageClasses.VillageType;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>SpriteLoader</h1>
 * <p>
 * A utility class responsible for loading sprite filenames from the classpath.
 * This class provides methods to load village, mountain, enemy, and structure sprites.
 * </p>
 * <p>
 * The SpriteLoader class is responsible for loading and managing the sprite filenames used
 * throughout the game. It provides methods for loading sprites from various directories
 * and for retrieving random sprites for different game elements.
 * </p>
 */
public class SpriteLoader {

    /**
     * Logger instance for logging events and errors.
     */
    private static final Logger logger = LoggerFactory.getLogger(SpriteLoader.class);

    /**
     * Path constants for sprite directories.
     */
    private static final String VILLAGE_SPRITE_PATH = "classpath:/static/assets/villages/";
    private static final String MOUNTAIN_SPRITE_PATH = "classpath:/static/assets/mountains/";
    private static final String ENEMY_SPRITE_PATH = "classpath:/static/assets/enemies/";
    private static final String STRUCTURE_BASE_PATH = "classpath:/static/assets/structures/";

    /**
     * Lists of loaded sprites. These are populated during static initialization.
     */
    public static final List<String> VILLAGE_SPRITES;
    public static final List<String> MOUNTAIN_SPRITES;
    public static final List<String> ENEMY_SPRITES;
    /**
     * Structure lists per village type.
     */
    public static final List<String> COMMON_STRUCTURE_SPRITES;
    public static final List<String> TIMBER_STRUCTURE_SPRITES;
    public static final List<String> ARMOR_STRUCTURE_SPRITES;
    public static final List<String> COMPOSITE_STRUCTURE_SPRITES;

    /**
     * Random number generator for sprite selection.
     */
    private static final Random rand = new Random();

    /**
     * Static block: Load all sprites once at application startup.
     * This ensures that all sprites are loaded and available when the game starts.
     */
    static {
        /**
         * Load village sprites.
         */
        VILLAGE_SPRITES = loadSprites(VILLAGE_SPRITE_PATH);
        /**
         * Load mountain sprites.
         */
        MOUNTAIN_SPRITES = loadSprites(MOUNTAIN_SPRITE_PATH);
        /**
         * Load enemy sprites.
         */
        ENEMY_SPRITES = loadSprites(ENEMY_SPRITE_PATH);

        /**
         * Load structure sprites for each village type.
         */
        COMMON_STRUCTURE_SPRITES = loadSprites(STRUCTURE_BASE_PATH + "CommonVillage-Houses/");
        TIMBER_STRUCTURE_SPRITES = loadSprites(STRUCTURE_BASE_PATH + "TimberVillage-Houses/");
        ARMOR_STRUCTURE_SPRITES = loadSprites(STRUCTURE_BASE_PATH + "ArmorVillage-Houses/");
        COMPOSITE_STRUCTURE_SPRITES = loadSprites(STRUCTURE_BASE_PATH + "CompositeVillage-Houses/");
    }

    /**
     * <h1>loadSprites Method</h1>
     * <p>
     * Generic method to load sprite filenames from a given path.
     * </p>
     * <p>
     * This method loads all sprite filenames from a given path in the classpath.
     * It uses a ResourcePatternResolver to find all the resources matching the specified pattern,
     * and then extracts the filenames from the resources.
     * </p>
     *
     * @param path The path to load sprites from.
     * @return A list of sprite filenames.
     */
    private static List<String> loadSprites(String path) {
        /**
         * Create a ResourcePatternResolver to resolve resources from the classpath.
         */
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        /**
         * Create a list to store the filenames of the loaded sprites.
         */
        List<String> filenames = new ArrayList<>();

        try {
            /**
             * Get all resources matching the specified path and ending with ".png".
             */
            Resource[] resources = resolver.getResources(path + "*.png");
            /**
             * Iterate through each resource.
             */
            for (Resource resource : resources) {
                /**
                 * Get the filename of the resource.
                 */
                String filename = resource.getFilename();
                /**
                 * Check if the filename is not null and does not contain "fountain" (case-insensitive).
                 */
                if (filename != null && !filename.toLowerCase().contains("fountain")) { // 🚫 Skip fountains
                    /**
                     * Add the filename to the list of filenames.
                     */
                    filenames.add(filename);
                    /**
                     * Log the loaded sprite.
                     */
                    logger.info("Loaded sprite: {} from {}", filename, path);
                } else {
                    /**
                     * Log the excluded fountain sprite.
                     */
                    logger.info("Excluded fountain sprite: {} from {}", filename, path);
                }
            }
            /**
             * Shuffle the list of filenames to randomize sprite selection.
             */
            Collections.shuffle(filenames);
        } catch (IOException e) {
            /**
             * Log an error if there is an issue loading sprites from the path.
             */
            logger.error("Error loading sprites from path: " + path, e);
        }

        /**
         * Return the list of sprite filenames.
         */
        return filenames;
    }


    /**
     * <h1>getRandomVillageSprite Method</h1>
     * <p>
     * Picks a random village sprite.
     * </p>
     *
     * @return full path to a random village sprite.
     */
    public static String getRandomVillageSprite() {
        /**
         * Check if the list of village sprites is empty.
         */
        if (VILLAGE_SPRITES.isEmpty()) {
            /**
             * Log a warning if no village sprites are found.
             */
            logger.warn("No village sprites found!");
            /**
             * Return a default village sprite path as a fallback.
             */
            return "/assets/villages/defaultVillage.png"; // fallback
        }
        /**
         * Return a random village sprite from the list.
         */
        return "/assets/villages/" + VILLAGE_SPRITES.get(rand.nextInt(VILLAGE_SPRITES.size()));
    }

    /**
     * <h1>getRandomMountainSprite Method</h1>
     * <p>
     * Picks a random mountain sprite.
     * </p>
     *
     * @return full path to a random mountain sprite.
     */
    public static String getRandomMountainSprite() {
        /**
         * Check if the list of mountain sprites is empty.
         */
        if (MOUNTAIN_SPRITES.isEmpty()) {
            /**
             * Log a warning if no mountain sprites are found.
             */
            logger.warn("No mountain sprites found!");
            /**
             * Return a default mountain sprite path as a fallback.
             */
            return "/assets/mountains/defaultMountain.png"; // fallback
        }
        /**
         * Return a random mountain sprite from the list.
         */
        return "/assets/mountains/" + MOUNTAIN_SPRITES.get(rand.nextInt(MOUNTAIN_SPRITES.size()));
    }

    /**
     * <h1>getRandomEnemySprite Method</h1>
     * <p>
     * Picks a random enemy sprite.
     * </p>
     *
     * @return full path to a random enemy sprite.
     */
    public static String getRandomEnemySprite() {
        /**
         * Check if the list of enemy sprites is empty.
         */
        if (ENEMY_SPRITES.isEmpty()) {
            /**
             * Log a warning if no enemy sprites are found.
             */
            logger.warn("No enemy sprites found!");
            /**
             * Return a default enemy sprite path as a fallback.
             */
            return "/assets/enemies/defaultEnemy.png"; // fallback
        }
        /**
         * Return a random enemy sprite from the list.
         */
        return "/assets/enemies/" + ENEMY_SPRITES.get(rand.nextInt(ENEMY_SPRITES.size()));
    }

    /**
     * <h1>getRandomStructureSprite Method</h1>
     * <p>
     * Picks a random structure sprite based on the village type.
     * </p>
     *
     * @param villageType the type of the village
     * @return full path to a random structure sprite.
     */
    public static String getRandomStructureSprite(VillageType villageType) {
        /**
         * Declare a list to store the sprite filenames.
         */
        List<String> spriteList;

        /**
         * Switch based on the village type.
         */
        switch (villageType) {
            /**
             * If the village type is COMMON.
             */
            case COMMON -> spriteList = COMMON_STRUCTURE_SPRITES;
            /**
             * If the village type is TIMBER.
             */
            case TIMBER -> spriteList = TIMBER_STRUCTURE_SPRITES;
            /**
             * If the village type is ARMOR.
             */
            case ARMOR -> spriteList = ARMOR_STRUCTURE_SPRITES;
            /**
             * If the village type is COMPOSITE.
             */
            case COMPOSITE -> spriteList = COMPOSITE_STRUCTURE_SPRITES;
            /**
             * Default case: fallback to common.
             */
            default -> spriteList = COMMON_STRUCTURE_SPRITES; // fallback to common
        }

        /**
         * Check if the sprite list is empty.
         */
        if (spriteList.isEmpty()) {
            /**
             * Log a warning if no structure sprites are found for the given village type.
             */
            logger.warn("No structure sprites found for type: {}", villageType);
            /**
             * Return a default structure sprite path as a fallback.
             */
            return "/assets/structures/defaultStructure.png"; // fallback
        }

        /**
         * Get a random filename from the sprite list.
         */
        String filename = spriteList.get(rand.nextInt(spriteList.size()));

        /**
         * Match the correct folder name based on the village type.
         */
        String folderName = switch (villageType) {
            case COMMON -> "CommonVillage-Houses";
            case TIMBER -> "TimberVillage-Houses";
            case ARMOR -> "ArmorVillage-Houses";
            case COMPOSITE -> "CompositeVillage-Houses";
        };

        /**
         * Return the full path to the random structure sprite.
         */
        /**
         * Return the full path to the random structure sprite.
         */
        return "/assets/structures/" + folderName + "/" + filename;
    }

    /**
     * <h1>getFountainSprite Method</h1>
     * <p>
     * Gets the fountain sprite path based on the village type.
     * This sprite is excluded from the random house sprites.
     * </p>
     *
     * @param villageType The type of the village.
     * @return The path to the fountain sprite.
     */
    public static String getFountainSprite(VillageType villageType) {
        // ========================= Determine the folder name based on the village type =========================
        String folder = switch (villageType) {
            case COMMON -> "CommonVillage-Houses";
            case TIMBER -> "TimberVillage-Houses";
            case ARMOR -> "ArmorVillage-Houses";
            case COMPOSITE -> "CompositeVillage-Houses";
        };
        // ========================= Determine the filename based on the village type =========================
        String filename = switch (villageType) {
            case COMMON -> "CommonVillageFountain.png";
            case TIMBER -> "TimberVillageFountain.png";
            case ARMOR -> "ArmorVillageFountain.png";
            case COMPOSITE -> "CompositeVillageFountain.png";
        };
        // ========================= Return the full path to the fountain sprite =========================
        return "/assets/structures/" + folder + "/" + filename;
    }


}

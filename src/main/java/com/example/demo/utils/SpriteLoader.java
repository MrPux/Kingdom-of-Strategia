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
 * A utility class responsible for loading sprite filenames from the classpath.
 * This class provides methods to load village, mountain, enemy, and structure sprites.
 */
public class SpriteLoader {

    private static final Logger logger = LoggerFactory.getLogger(SpriteLoader.class);

    // Path constants
    private static final String VILLAGE_SPRITE_PATH = "classpath:/static/assets/villages/";
    private static final String MOUNTAIN_SPRITE_PATH = "classpath:/static/assets/mountains/";
    private static final String ENEMY_SPRITE_PATH = "classpath:/static/assets/enemies/";
    private static final String STRUCTURE_BASE_PATH = "classpath:/static/assets/structures/";

    // Lists of loaded sprites
    public static final List<String> VILLAGE_SPRITES;
    public static final List<String> MOUNTAIN_SPRITES;
    public static final List<String> ENEMY_SPRITES;
    // Structure lists per village type
    public static final List<String> COMMON_STRUCTURE_SPRITES;
    public static final List<String> TIMBER_STRUCTURE_SPRITES;
    public static final List<String> ARMOR_STRUCTURE_SPRITES;
    public static final List<String> COMPOSITE_STRUCTURE_SPRITES;

    private static final Random rand = new Random();

    // Static block: Load all sprites once at application startup
    static {
        VILLAGE_SPRITES = loadSprites(VILLAGE_SPRITE_PATH);
        MOUNTAIN_SPRITES = loadSprites(MOUNTAIN_SPRITE_PATH);
        ENEMY_SPRITES = loadSprites(ENEMY_SPRITE_PATH);

        COMMON_STRUCTURE_SPRITES = loadSprites(STRUCTURE_BASE_PATH + "CommonVillage-Houses/");
        TIMBER_STRUCTURE_SPRITES = loadSprites(STRUCTURE_BASE_PATH + "TimberVillage-Houses/");
        ARMOR_STRUCTURE_SPRITES = loadSprites(STRUCTURE_BASE_PATH + "ArmorVillage-Houses/");
        COMPOSITE_STRUCTURE_SPRITES = loadSprites(STRUCTURE_BASE_PATH + "CompositeVillage-Houses/");
    }

    /**
     * Generic method to load sprite filenames from a given path.
     *
     * @param path The path to load sprites from.
     * @return A list of sprite filenames.
     */
    private static List<String> loadSprites(String path) {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        List<String> filenames = new ArrayList<>();
    
        try {
            Resource[] resources = resolver.getResources(path + "*.png");
            for (Resource resource : resources) {
                String filename = resource.getFilename();
                if (filename != null && !filename.toLowerCase().contains("fountain")) { // 🚫 Skip fountains
                    filenames.add(filename);
                    logger.info("Loaded sprite: {} from {}", filename, path);
                } else {
                    logger.info("Excluded fountain sprite: {} from {}", filename, path);
                }
            }
            Collections.shuffle(filenames);
        } catch (IOException e) {
            logger.error("Error loading sprites from path: " + path, e);
        }
    
        return filenames;
    }
    

    /**
     * Picks a random village sprite.
     *
     * @return full path to a random village sprite.
     */
    public static String getRandomVillageSprite() {
        if (VILLAGE_SPRITES.isEmpty()) {
            logger.warn("No village sprites found!");
            return "/assets/villages/defaultVillage.png"; // fallback
        }
        return "/assets/villages/" + VILLAGE_SPRITES.get(rand.nextInt(VILLAGE_SPRITES.size()));
    }

    /**
     * Picks a random mountain sprite.
     *
     * @return full path to a random mountain sprite.
     */
    public static String getRandomMountainSprite() {
        if (MOUNTAIN_SPRITES.isEmpty()) {
            logger.warn("No mountain sprites found!");
            return "/assets/mountains/defaultMountain.png"; // fallback
        }
        return "/assets/mountains/" + MOUNTAIN_SPRITES.get(rand.nextInt(MOUNTAIN_SPRITES.size()));
    }

    /**
     * Picks a random enemy sprite.
     *
     * @return full path to a random enemy sprite.
     */
    public static String getRandomEnemySprite() {
        if (ENEMY_SPRITES.isEmpty()) {
            logger.warn("No enemy sprites found!");
            return "/assets/enemies/defaultEnemy.png"; // fallback
        }
        return "/assets/enemies/" + ENEMY_SPRITES.get(rand.nextInt(ENEMY_SPRITES.size()));
    }

    /**
     * Picks a random structure sprite based on the village type.
     *
     * @param villageType the type of the village
     * @return full path to a random structure sprite.
     */
    public static String getRandomStructureSprite(VillageType villageType) {
        List<String> spriteList;

        switch (villageType) {
            case COMMON -> spriteList = COMMON_STRUCTURE_SPRITES;
            case TIMBER -> spriteList = TIMBER_STRUCTURE_SPRITES;
            case ARMOR -> spriteList = ARMOR_STRUCTURE_SPRITES;
            case COMPOSITE -> spriteList = COMPOSITE_STRUCTURE_SPRITES;
            default -> spriteList = COMMON_STRUCTURE_SPRITES; // fallback to common
        }

        if (spriteList.isEmpty()) {
            logger.warn("No structure sprites found for type: {}", villageType);
            return "/assets/structures/defaultStructure.png"; // fallback
        }

        String filename = spriteList.get(rand.nextInt(spriteList.size()));

        // Match the correct folder name
        String folderName = switch (villageType) {
            case COMMON -> "CommonVillage-Houses";
            case TIMBER -> "TimberVillage-Houses";
            case ARMOR -> "ArmorVillage-Houses";
            case COMPOSITE -> "CompositeVillage-Houses";
        };

        return "/assets/structures/" + folderName + "/" + filename;
    }

    /**
     * Gets the fountain sprite path based on the village type.
     * This sprite is excluded from the random house sprites.
     *
     * @param villageType The type of the village.
     * @return The path to the fountain sprite.
     */
    public static String getFountainSprite(VillageType villageType) {
        String folder = switch (villageType) {
            case COMMON -> "CommonVillage-Houses";
            case TIMBER -> "TimberVillage-Houses";
            case ARMOR -> "ArmorVillage-Houses";
            case COMPOSITE -> "CompositeVillage-Houses";
        };
        String filename = switch (villageType) {
            case COMMON -> "CommonVillageFountain.png";
            case TIMBER -> "TimberVillageFountain.png";
            case ARMOR -> "ArmorVillageFountain.png";
            case COMPOSITE -> "CompositeVillageFountain.png";
        };
        return "/assets/structures/" + folder + "/" + filename;
    }
    

}

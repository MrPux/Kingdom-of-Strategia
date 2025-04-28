package com.example.demo.utils;

import com.example.demo.mechanics.generation.GameMap;
import com.example.demo.mechanics.generation.GameMapGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <h1>GameConfig</h1>
 * <p>
 * Configuration class for the game.
 * </p>
 * <p>
 * The GameConfig class is responsible for configuring the game by creating and providing
 * beans that are used throughout the application.
 * </p>
 */
@Configuration
public class GameConfig {

    /**
     * <h1>gameMap Method</h1>
     * <p>
     * Creates and configures the game map.
     * </p>
     * <p>
     * This method creates a new GameMap object by calling the generateMap method of the
     * GameMapGenerator class. The GameMap object is then made available as a bean in the
     * Spring application context.
     * </p>
     *
     * @return The generated game map.
     */
    @Bean
    public GameMap gameMap() {
        return GameMapGenerator.generateMap(); // ✅ Corrected
    }
}

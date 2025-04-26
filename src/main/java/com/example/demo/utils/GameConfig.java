package com.example.demo.utils;

import com.example.demo.mechanics.generation.GameMap;
import com.example.demo.mechanics.generation.GameMapGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for the game.
 */
@Configuration
public class GameConfig {

    /**
     * Creates and configures the game map.
     *
     * @return The generated game map.
     */
    @Bean
    public GameMap gameMap() {
        return GameMapGenerator.generateMap(); // ✅ Corrected
    }
}

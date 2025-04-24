package com.example.demo.utils;

import com.example.demo.mechanics.generation.GameMap;
import com.example.demo.mechanics.generation.GameMapGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfig {

    @Bean
    public GameMap gameMap() {
        return GameMapGenerator.generateMap(); // ✅ Corrected
    }
}

package com.example.demo.utils;

import com.example.demo.core.Village;

public class GameUtils {
    public static int calculateDistance(Village v1, Village v2) {
        int dx = v1.getXCoordinate() - v2.getXCoordinate();
        int dy = v1.getYCoordinate() - v2.getYCoordinate();
        return (int) Math.sqrt(dx * dx + dy * dy); // Euclidean distance
    }
}

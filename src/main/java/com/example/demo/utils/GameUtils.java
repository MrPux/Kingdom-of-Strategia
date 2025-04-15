package com.example.demo.utils;
import com.example.demo.core.Village;

public class GameUtils {
    public static int calculateDistance(Village village1, Village village2)
    {
        int distanceX = village1.getXCordinate() - village2.getXCordinate();
        int distanceY = village2.getYCordinate() - village2.getYCordinate();
        return (int) Math.sqrt(distanceX * distanceY + distanceY * distanceY);
    }
}

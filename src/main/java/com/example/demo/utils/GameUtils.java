/**
 * This class provides utility functions for the game.
 */
package com.example.demo.utils;

import com.example.demo.classes.Mountain;
import com.example.demo.classes.Village;  
 
public class GameUtils {
    public static int calculateDistance(Village v1, Village v2) {
        // Calculate the difference in x-coordinates
        int deltaX = v1.getXCoordinate() - v2.getXCoordinate();
        // Calculate the difference in y-coordinates
        int deltaY = v1.getYCoordinate() - v2.getYCoordinate();
        return (int) Math.sqrt(deltaX * deltaX + deltaY * deltaY); // Euclidean distance
    }
 
    public static boolean lineIntersectsMountain(Village village1, Village village2, Mountain mountain, int mountainRadius) {
        // Get the x and y coordinates of the first village
        int x1 = village1.getXCoordinate();
        int y1 = village1.getYCoordinate();
        // Get the x and y coordinates of the second village
        int x2 = village2.getXCoordinate();
        int y2 = village2.getYCoordinate();
        // Get the x and y coordinates of the mountain
        int circleX = mountain.getXCoordinate();
        int circleY = mountain.getYCoordinate();
    
        // Calculate the difference in x-coordinates
        double deltaX = x2 - x1;
        // Calculate the difference in y-coordinates
        double deltaY = y2 - y1;
    
        // Calculate the coefficients for the quadratic equation
        double a = deltaX * deltaX + deltaY * deltaY;
        double b = 2 * (deltaX * (x1 - circleX) + deltaY * (y1 - circleY));
        double c = circleX * circleX + circleY * circleY + x1 * x1 + y1 * y1 - 2 * (circleX * x1 + circleY * y1) - mountainRadius * mountainRadius;
    
        // Calculate the discriminant
        double discriminant = b * b - 4 * a * c;
        return discriminant >= 0;
    }
 
    public static boolean lineIntersectsCircle(int x1, int y1, int x2, int y2, int circleX, int circleY, int radius) {
        double dx = x2 - x1;
        double dy = y2 - y1;
    
        double fx = x1 - circleX;
        double fy = y1 - circleY;
    
        double a = dx * dx + dy * dy;
        double b = 2 * (fx * dx + fy * dy);
        double c = (fx * fx + fy * fy) - radius * radius;
    
        double discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            return false; // no intersection
        }
    
        discriminant = Math.sqrt(discriminant);
        double t1 = (-b - discriminant) / (2 * a);
        double t2 = (-b + discriminant) / (2 * a);
    
        return (t1 >= 0 && t1 <= 1) || (t2 >= 0 && t2 <= 1);
    }

    public static int calculateDistance(int x1, int y1, int x2, int y2) {
        // Calculate the distance between two points
        return (int) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }
    
}

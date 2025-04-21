/**
 * This class provides utility functions for the game.
 */
package com.example.demo.utils;

import com.example.demo.core.Mountain;
import com.example.demo.core.Village;  
 
public class GameUtils {
    public static int calculateDistance(Village v1, Village v2) {
        int dx = v1.getXCoordinate() - v2.getXCoordinate();
        int dy = v1.getYCoordinate() - v2.getYCoordinate();
        return (int) Math.sqrt(dx * dx + dy * dy); // Euclidean distance
    }
 
    
    public static boolean lineIntersectsMountain(Village v1, Village v2, Mountain mountain, int radius) {
        int x1 = v1.getXCoordinate();
        int y1 = v1.getYCoordinate();
        int x2 = v2.getXCoordinate();
        int y2 = v2.getYCoordinate();
        int cx = mountain.getXCoordinate();
        int cy = mountain.getYCoordinate();
    
        double dx = x2 - x1;
        double dy = y2 - y1;
    
        double a = dx * dx + dy * dy;
        double b = 2 * (dx * (x1 - cx) + dy * (y1 - cy));
        double c = cx * cx + cy * cy + x1 * x1 + y1 * y1 - 2 * (cx * x1 + cy * y1) - radius * radius;
    
        double discriminant = b * b - 4 * a * c;
        return discriminant >= 0;
    }

    public static boolean lineIntersectsCircle(int x1, int y1, int x2, int y2, int cx, int cy, int radius) {
        double dx = x2 - x1;
        double dy = y2 - y1;
    
        double fx = x1 - cx;
        double fy = y1 - cy;
    
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

    public static int calculateDistance(int xCoordinate, int yCoordinate, int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateDistance'");
    }
    
}

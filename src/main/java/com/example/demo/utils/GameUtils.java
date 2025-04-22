/**
 * This class provides utility functions for the game.
 */
package com.example.demo.utils;

import com.example.demo.classes.Mountain;
import com.example.demo.classes.Village;

/**
 * This class provides utility functions for the game.
 */
public class GameUtils {
    /**
     * Calculates the Euclidean distance between two villages.
     * @param v1 The first village.
     * @param v2 The second village.
     * @return The distance between the two villages.
     */
    public static int calculateDistance(Village v1, Village v2) {
        // Calculate the difference in x-coordinates
        int deltaX = v1.getXCoordinate() - v2.getXCoordinate();
        // Calculate the difference in y-coordinates
        int deltaY = v1.getYCoordinate() - v2.getYCoordinate();
        return (int) Math.sqrt(deltaX * deltaX + deltaY * deltaY); // Euclidean distance
    }

    /**
     * Checks if a line segment between two villages intersects a mountain.
     * @param village1 The first village.
     * @param village2 The second village.
     * @param mountain The mountain to check for intersection.
     * @param mountainRadius The radius of the mountain.
     * @return True if the line intersects the mountain, false otherwise.
     */
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

    /**
     * Checks if a line segment intersects a circle.
     * @param x1 The x-coordinate of the first point.
     * @param y1 The y-coordinate of the first point.
     * @param x2 The x-coordinate of the second point.
     * @param y2 The y-coordinate of the second point.
     * @param circleX The x-coordinate of the circle's center.
     * @param circleY The y-coordinate of the circle's center.
     * @param radius The radius of the circle.
     * @return True if the line intersects the circle, false otherwise.
     */
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

    /**
     * Calculates the Euclidean distance between two points.
     * @param x1 The x-coordinate of the first point.
     * @param y1 The y-coordinate of the first point.
     * @param x2 The x-coordinate of the second point.
     * @param y2 The y-coordinate of the second point.
     * @return The distance between the two points.
     */
    public static int calculateDistance(int x1, int y1, int x2, int y2) {
        // Calculate the distance between two points
        return (int) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }
}

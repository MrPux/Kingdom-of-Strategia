/**
 * This class provides general utility functions for the game, such as distance calculation and intersection checks.
 */
package com.example.demo.utils;

import com.example.demo.classes.Mountain;
import com.example.demo.classes.villageClasses.Village;

import java.util.Random;

/**
 * <h1>GameUtils</h1>
 * <p>
 * This class provides general utility functions for the game, such as distance calculation and intersection checks.
 * </p>
 * <p>
 * The GameUtils class provides static methods for performing common calculations and checks
 * that are used throughout the game, such as calculating the distance between two villages or
 * checking if a line segment intersects a circle.
 * </p>
 */
public class GameUtils {
    /**
     * <h1>calculateDistance Method</h1>
     * <p>
     * Calculates the Euclidean distance between two villages.
     * </p>
     *
     * @param village1 The first village.
     * @param village2 The second village.
     * @return The distance between the two villages.
     */
    public static int calculateDistance(Village village1, Village village2) {
        // Calculate the difference in x-coordinates between the two villages.
        int deltaX = village1.getXCoordinate() - village2.getXCoordinate();
        // Calculate the difference in y-coordinates between the two villages.
        int deltaY = village1.getYCoordinate() - village2.getYCoordinate();
        // Calculate the Euclidean distance using the Pythagorean theorem.
        int distance = (int) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        Random rand = new Random();
        int randomFactor = rand.nextInt(50); // Add a random number between 0 and 49
        return distance + randomFactor;
    }

    /**
     * <h1>lineIntersectsMountain Method</h1>
     * <p>
     * Checks if a line segment between two villages intersects a mountain.
     * </p>
     *
     * @param village1       The first village.
     * @param village2       The second village.
     * @param mountain       The mountain to check for intersection against.
     * @param mountainRadius The radius of the mountain.
     * @return True if the line intersects the mountain, false otherwise.
     */
    public static boolean lineIntersectsMountain(Village village1, Village village2, Mountain mountain, int mountainRadius) {
        // Get the x and y coordinates of the first village.
        int village1X = village1.getXCoordinate();
        int village1Y = village1.getYCoordinate();
        // Get the x and y coordinates of the second village.
        int village2X = village2.getXCoordinate();
        int village2Y = village2.getYCoordinate();
        // Get the x and y coordinates of the mountain.
        int mountainX = mountain.getXCoordinate();
        int mountainY = mountain.getYCoordinate();

        // Calculate the difference in x-coordinates between the two villages.
        double deltaX = village2X - village1X;
        // Calculate the difference in y-coordinates between the two villages.
        double deltaY = village2Y - village1Y;

        // Calculate the coefficients for the quadratic equation.
        double a = deltaX * deltaX + deltaY * deltaY;
        double b = 2 * (deltaX * (village1X - mountainX) + deltaY * (village1Y - mountainY));
        double c = mountainX * mountainX + mountainY * mountainY + village1X * village1X + village1Y * village1Y - 2 * (mountainX * village1X + mountainY * village1Y) - mountainRadius * mountainRadius;

        // Calculate the discriminant of the quadratic equation.
        double discriminant = b * b - 4 * a * c;
        // If the discriminant is greater than or equal to 0, the line intersects the mountain.
        return discriminant >= 0;
    }

    /**
     * <h1>lineIntersectsCircle Method</h1>
     * <p>
     * Checks if a line segment intersects a circle.
     * </p>
     *
     * @param lineStartX    The x-coordinate of the line segment's starting point.
     * @param lineStartY    The y-coordinate of the line segment's starting point.
     * @param lineEndX      The x-coordinate of the line segment's ending point.
     * @param lineEndY      The y-coordinate of the line segment's ending point.
     * @param circleCenterX The x-coordinate of the circle's center.
     * @param circleCenterY The y-coordinate of the circle's center.
     * @param circleRadius  The radius of the circle.
     * @return True if the line intersects the circle, false otherwise.
     */
    public static boolean lineIntersectsCircle(int lineStartX, int lineStartY, int lineEndX, int lineEndY, int circleCenterX, int circleCenterY, int circleRadius) {
        // Calculate the difference in x-coordinates between the line's end and start points.
        double deltaX = lineEndX - lineStartX;
        // Calculate the difference in y-coordinates between the line's end and start points.
        double deltaY = lineEndY - lineStartY;

        // Calculate the x-component of the vector from the circle's center to the line's start point.
        double fX = lineStartX - circleCenterX;
        // Calculate the y-component of the vector from the circle's center to the line's start point.
        double fY = lineStartY - circleCenterY;

        // Calculate the coefficients for the quadratic equation.
        double aCoefficient = deltaX * deltaX + deltaY * deltaY;
        double bCoefficient = 2 * (fX * deltaX + fY * deltaY);
        double cCoefficient = (fX * fX + fY * fY) - circleRadius * circleRadius;

        // Calculate the discriminant of the quadratic equation.
        double discriminantValue = bCoefficient * bCoefficient - 4 * aCoefficient * cCoefficient;
        // If the discriminant is less than 0, the line does not intersect the circle.
        if (discriminantValue < 0) {
            return false;
        }

        // Calculate the parameters t1 and t2, which represent the points of intersection.
        discriminantValue = Math.sqrt(discriminantValue);
        double parameter1 = (-bCoefficient - discriminantValue) / (2 * aCoefficient);
        double parameter2 = (-bCoefficient + discriminantValue) / (2 * aCoefficient);

        // Check if either parameter is within the range [0, 1]. If so, the line intersects the circle.
        return (parameter1 >= 0 && parameter1 <= 1) || (parameter2 >= 0 && parameter2 <= 1);
    }

    /**
     * <h1>calculateDistance Method</h1>
     * <p>
     * Calculates the Euclidean distance between two points.
     * </p>
     *
     * @param point1X The x-coordinate of the first point.
     * @param point1Y The y-coordinate of the first point.
     * @param point2X The x-coordinate of the second point.
     * @param point2Y The y-coordinate of the second point.
     * @return The distance between the two points.
     */
    public static int calculateDistance(int point1X, int point1Y, int point2X, int point2Y) {
        // Calculate the Euclidean distance using the Pythagorean theorem.
        return (int) Math.sqrt((point2X - point1X) * (point2X - point1X) + (point2Y - point1Y) * (point2Y - point1Y));
    }
}

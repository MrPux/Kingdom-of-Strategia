package com.example.demo.classes;

public class Enemy 
{
    private final int xCoordinate, yCoordinate; 
    private final int level;

    public Enemy(int x, int y, int level)
    {
        this.xCoordinate = x; this.yCoordinate = y; this.level = level;
    }

    //Getters
    public int getXCoordinate(){return this.xCoordinate;}
    public int getYCoordinate(){return this.yCoordinate;}
    public int getLevel(){return this.level;}
}

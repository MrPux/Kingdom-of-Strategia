package com.example.demo.core;

public class Mountain
{
    private int xCoordinate;
    private int yCoordinate;

    public Mountain(int xCoordinate, int yCoordinate)
    {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public int getXCoordinate(){return this.xCoordinate;}
    public int getYCoordinate(){return this.yCoordinate;}
}

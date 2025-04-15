package com.example.demo.mechanics.generation;

import com.example.demo.core.Road;
import com.example.demo.core.Village;
import com.example.demo.utils.GameUtils;
import com.example.demo.utils.LinkedList;
import java.util.Random;

public class MapGenerator {
    //Instance variables
    private static final int MAX_VILLAGES = 10;
    private static final int MAX_X = 800;
    private static final int MAX_Y = 600;

    public static GameMap generateMap()
    {
        GameMap map = new GameMap();
        Random rand = new Random();

        LinkedList<Village> villages = new LinkedList<>();
        for(int i = 0; i < MAX_VILLAGES; i++)
        {
            int x = rand.nextInt(MAX_X);
            int y = rand.nextInt(MAX_Y);
            Village village = new Village("Village" + i, i, x, y);
            villages.add(village);
        }

        for(int i = 0; i < villages.size() -1; i++)
        {
            Village village1 = villages.get(i);
            Village village2 = villages.get(i + 1);
            Road road = new Road(village1, village2, GameUtils.calculateDistance(village1, village2));
        }

        return map;
    }
}

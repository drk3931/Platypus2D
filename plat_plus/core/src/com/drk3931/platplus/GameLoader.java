package com.drk3931.platplus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Polygon;

class GameLoader{


    private World gameWorld;
    private Map gameMap;

    public GameLoader()
    {

        gameWorld = new World();
        gameMap = new Map();

        gameWorld.worldCharacters = new CharacterEntity[1];
        gameWorld.worldCharacters[0] = gameWorld.getPlayer().characterEntity;


        gameMap.mapPolies = new Polygon[10];
    
        gameMap.mapPolies[0] = gameMap.genTriangle(0, 0, 32);

        for(int i = 1; i < gameMap.mapPolies.length; i++)
        {

            gameMap.mapPolies[i] = gameMap.genRect(i * 32, 0, 32, 32);
            
        }

        

    }

    public World getLoadedWorld()
    {
        return this.gameWorld;
    }

    public Map getLoadedMap()
    {
        return this.gameMap;
    }
}
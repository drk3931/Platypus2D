package com.drk3931.platplus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Shape2D;

class GameLoader{


    private World gameWorld;
    private Map gameMap;


    private void loadMap()
    {

        gameMap = new Map();
        gameMap.mapPolies = new Shape2D[10];
        
    
        for(int i = 0; i < gameMap.mapPolies.length; i++)
        {

            boolean random = Math.random() * 50 > 25? true: false;

            if(random)
            {
                gameMap.mapPolies[i] = gameMap.genRect(i * 32, 0, 32, 32);

            }
            else{
                gameMap.mapPolies[i] = gameMap.genCircle((i * 32) + 16, 16, 16);

            }
            
        }

    }

    public GameLoader()
    {

        gameWorld = new World();

        loadMap();

        gameWorld.worldCharacters = new CharacterEntity[1];
        gameWorld.worldCharacters[0] = gameWorld.getPlayer().characterEntity;


      

        

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
package com.drk3931.platplus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

class GameLoader{


    private World gameWorld;
    private Map gameMap;


    public static TextureRegion getTexRegion(String name)
    {
        return new TextureRegion(new Texture(Gdx.files.internal(name)));
    }


    public World loadworld()
    {
        gameWorld = new World();
        gameMap.parseMapEnemies(gameWorld);

        return gameWorld;
    }

    public Map loadMap()
    {

        gameMap = new Map("plat_plus_l1.tmx");
        return gameMap;
        
        /*
            parse characters from map and put them in the world
        */

      
        
    }

    public GameLoader()
    {



        loadMap();


    }

 
}
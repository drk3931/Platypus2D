package com.drk3931.platplus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Shape2D;

class GameLoader{


    private World gameWorld;
    private Map gameMap;


    private void loadMap()
    {

        gameMap = new Map("plat_plus_l1.tmx");

        /*
            parse characters from map and put them in the world
        */

      
        
    }

    public GameLoader()
    {

        gameWorld = new World();


        loadMap();


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
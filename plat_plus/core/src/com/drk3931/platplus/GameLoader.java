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
;

        gameWorld = new World();
        gameMap = new Map("plat_plus_l1.tmx");

        gameWorld.worldCharacters = new CharacterEntity[1];
        gameWorld.worldCharacters[0] = gameWorld.getPlayer().characterEntity;



      
        
    }

    public GameLoader()
    {

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
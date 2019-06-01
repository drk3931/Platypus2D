package com.drk3931.platplus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Shape2D;

class GameLoader{


    private World gameWorld;
    private Map gameMap;


    private void loadMap()
    {

        gameMap = new Map();
        TiledMap map = gameMap.tiledMap;

        TiledMapTileLayer collLayer = (TiledMapTileLayer)map.getLayers().get("Blocked");

        int numTiles = collLayer.getObjects().getCount();

        
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
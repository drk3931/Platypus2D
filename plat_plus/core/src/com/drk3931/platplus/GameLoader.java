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

        // = new Map();
        //TiledMap map = gameMap.tiledMap;

        //TiledMapTileLayer collLayer = (TiledMapTileLayer)map.getLayers().get("Blocked");

        //int numTiles = collLayer.getObjects().getCount();

        gameWorld = new World();
        gameMap = new Map("plat_plus_l1.tmx");

        gameWorld.worldCharacters = new CharacterEntity[1];
        gameWorld.worldCharacters[0] = gameWorld.getPlayer().characterEntity;


        gameMap.mapPolies = new Shape2D[40];



        for(int i = 0; i < gameMap.mapPolies.length; i++)
        {


            
        }

        
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
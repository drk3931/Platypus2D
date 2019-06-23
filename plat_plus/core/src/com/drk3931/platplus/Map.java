package com.drk3931.platplus;


import java.util.ArrayList;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;


class Map{

    private ArrayList<Shape2D> mapPolies;
    private TiledMap tiledMap;
    private final int CELL_W,CELL_H;


    public TiledMap getTiledMap()
    {
        return this.tiledMap;
    }

    public ArrayList<Shape2D> getMapPolies()
    {
        return this.mapPolies;
    }


    public Map(String mapFileName)
    {
       
        mapPolies = new ArrayList<Shape2D>();
        tiledMap = new TmxMapLoader().load(mapFileName);
        TiledMapTileLayer layer = (TiledMapTileLayer)tiledMap.getLayers().get("Terrain"); // assuming the layer at index on contains tiles
        
        CELL_W = (int)layer.getTileWidth();
        CELL_H = (int)layer.getTileHeight();

        
        for(int i = 0; i < layer.getWidth(); i++)
        {
            for(int j =0; j < layer.getHeight(); j++)
            {
                Cell cell = layer.getCell(i, j);
        
                
                if(cell != null)
                {
                    mapPolies.add(genRect(i * CELL_W, j * CELL_H, CELL_W, CELL_H));
                }
            }
        }

    }

    public Shape2D genRect(int x,int y, int w, int h)
    {


        return new Rectangle(x, y, w, h);

        /*
        return new Polygon(new float[]{
            x,y,x,y+h,x+w,y+h,x+w,y
        });
        */
    }


    public Shape2D genCircle(int x,int y, int radius)
    {

        return new Circle(x, y, radius);
        
    }

    public Shape2D genLineSeg(int x1,int y1,int x2,int y2)
    {
        return new Polyline(new float[]{x1,y1,x2,y2});
    }
    
    

}
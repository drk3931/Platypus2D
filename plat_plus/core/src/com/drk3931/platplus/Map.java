package com.drk3931.platplus;


import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;


class Map{

    public Shape2D[] mapPolies;
    public TiledMap tiledMap;

    public Map()
    {
       
        tiledMap = new TmxMapLoader().load("testMap.tmx");
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
    
    

}
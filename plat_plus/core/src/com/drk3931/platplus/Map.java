package com.drk3931.platplus;


import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;


class Map{

    public Shape2D[] mapPolies;
    public TiledMap tiledMap;

    public Map(String mapFileName)
    {
       
        tiledMap = new TmxMapLoader().load(mapFileName);
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
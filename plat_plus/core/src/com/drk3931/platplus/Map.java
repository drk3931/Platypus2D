package com.drk3931.platplus;

import com.badlogic.gdx.math.Polygon;


class Map{

    public Polygon[] mapPolies;
    

    public Map()
    {
       
        
    }

    public Polygon genRect(int x,int y, int w, int h)
    {
        return new Polygon(new float[]{
            x,y,x,y+h,x+w,y+h,x+w,y
        });
    }


    public Polygon genTriangle(int x,int y, int leg)
    {
        return new Polygon(new float[]{
            x,y,x,y+leg,x+leg,y
        });
    }
    
    

}
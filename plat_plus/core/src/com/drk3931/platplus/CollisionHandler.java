package com.drk3931.platplus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

class CollisionHandler{

    private World world;
    private Map map;
    private Rectangle overlappingRectangle;


    public CollisionHandler(Map map, World world) {
        this.world = world;
        this.map = map;
        this.overlappingRectangle = new Rectangle();
        slopeVector = new Vector2(1,1);
    }

    Vector2 slopeVector;


    public void update(float delta)
    {
        GeometricRepresentation playerGeo = world.geoRep;
        Rectangle playerRect = (Rectangle)world.geoRep.shapeRepresentation;


        for(Shape2D shape: map.getMapPolies())
        {

            if(shape.getClass() == Polyline.class)
            {
                Polyline p  = (Polyline)shape;
                float[] vertices = p.getTransformedVertices();
                if(Intersector.intersectSegmentRectangle(vertices[0], vertices[1], vertices[2], vertices[3], playerRect)){
                    playerGeo.translate(world.lastChangeX * -1, world.lastChangeY * -1);
                    if(vertices[1] < vertices[3] && Math.signum(world.lastChangeX) > 0)
                    {
                        //upwards slope
                        playerGeo.translate(world.lastChangeX, world.lastChangeX);
                    }
                    else{
                        //downwards slope
                        playerGeo.translate(world.lastChangeX, world.lastChangeX * -1);

                    }
                }
            }


            if(shape.getClass() == Rectangle.class)
            {
                Rectangle tileAsRect = (Rectangle)shape;

                //System.out.println(tileAsRect.toString());

                while(Intersector.intersectRectangles(playerRect, tileAsRect,overlappingRectangle))
                {

                    playerGeo.translate(Math.signum(world.lastChangeX) * -1, Math.signum(world.lastChangeY) * -1);

                    
                }
            


            }
            
        }
    }
    
}
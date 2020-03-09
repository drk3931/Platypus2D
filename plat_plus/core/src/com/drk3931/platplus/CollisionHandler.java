package com.drk3931.platplus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

class CollisionHandler {

    private World world;
    private Map map;
    private Rectangle overlappingRectangle;

    public CollisionHandler(Map map, World world) {
        this.world = world;
        this.map = map;
        this.overlappingRectangle = new Rectangle();
    }

    public void update(float delta) {
        GeometricRepresentation playerGeo = world.geoRep;
        Rectangle playerRect = (Rectangle) world.geoRep.shapeRepresentation;

        /*
         * 
         * Move the player along the X axis. Check for colliding tiles. Resolve X
         * collision. Move the player along the Y axis. Check for colliding tiles.
         * Resolve Y collision
         */

        playerGeo.translate(world.lastChangeX, 0);
        for (Shape2D shape : map.getMapPolies()) {

            if (shape.getClass() == Rectangle.class) {
                Rectangle tileAsRect = (Rectangle) shape;

                if (Intersector.intersectRectangles(playerRect, tileAsRect, overlappingRectangle)) {
                    
                    if(world.lastChangeX < 0)
                    {
                        playerGeo.translate(  overlappingRectangle.width, 0 );

                    }
                    else{
                        playerGeo.translate(  overlappingRectangle.width * -1, 0 );

                    }

                }

            }
        }
        playerGeo.translate(0, world.lastChangeY);

        for (Shape2D shape : map.getMapPolies()) {

            if (shape.getClass() == Rectangle.class) {
                Rectangle tileAsRect = (Rectangle) shape;

                if (Intersector.intersectRectangles(playerRect, tileAsRect, overlappingRectangle)) {


                    if(world.lastChangeY < 0)
                    {
                        playerGeo.translate(  0, overlappingRectangle.height );

                    }
                    else{
                        playerGeo.translate(  0, overlappingRectangle.height * -1);

                    }
                }

            }
        }

        for (Shape2D shape : map.getMapPolies()) {

            if (shape.getClass() == Polyline.class) {
                Polyline p = (Polyline) shape;
                float[] vertices = p.getTransformedVertices();
                if (Intersector.intersectSegmentRectangle(vertices[0], vertices[1], vertices[2], vertices[3],
                        playerRect)) {
                    playerGeo.translate(world.lastChangeX * -1, world.lastChangeY * -1);
                    if (vertices[1] < vertices[3] && Math.signum(world.lastChangeX) > 0) {
                        // upwards slope
                        playerGeo.translate(world.lastChangeX, world.lastChangeX);
                    } else {
                        // downwards slope
                        playerGeo.translate(world.lastChangeX, world.lastChangeX * -1);

                    }
                }
            }

        }
    }

}
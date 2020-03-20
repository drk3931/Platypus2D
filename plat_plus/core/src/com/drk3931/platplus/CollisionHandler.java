package com.drk3931.platplus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;

class CollisionHandler {

    private World world;
    private Map map;
    private Player player;
    private Rectangle overlappingRectangle;

    public CollisionHandler(Map map, World world) {
        this.world = world;
        this.map = map;
        this.player = world.getPlayer();
        this.overlappingRectangle = new Rectangle();
    }

    public void update(float delta) {

        GeometricRepresentation playerGeo = player.e.getGeoRep();
        Rectangle playerRect = (Rectangle) playerGeo.shapeRepresentation;

        float playerVelX = player.e.getVelocityX(),playerVelY = player.e.getVelocityY();
        

        /*
         * 
         * Move the player along the X axis. Check for colliding tiles. Resolve X
         * collision. Move the player along the Y axis. Check for colliding tiles.
         * Resolve Y collision
         */


        player.e.moveX();


        for (Shape2D shape : map.getMapPolies()) {

            if (shape.getClass() == Polyline.class) {
                Polyline p = (Polyline) shape;
                float[] vertices = p.getTransformedVertices();
                if (Intersector.intersectSegmentRectangle(vertices[0], vertices[1], vertices[2], vertices[3],
                        playerRect)) {
        
                    if (vertices[1] < vertices[3] && Math.signum(playerVelX) > 0) {
                        // upwards slope

                        //move backwards and recorrect upwards 
                        playerGeo.translate(playerVelX * -1, 0);
                        playerGeo.translate(Math.abs(playerVelX), Math.abs(playerVelX));


                    } else {
                        // downwards slope
                        playerGeo.translate(playerVelX * -1, 0);
                        playerGeo.translate(playerVelX, Math.abs(playerVelX));

                    }
                }
            }

        }
        for (Shape2D shape : map.getMapPolies()) {

            if (shape.getClass() == Rectangle.class) {
                Rectangle tileAsRect = (Rectangle) shape;

                if (Intersector.intersectRectangles(playerRect, tileAsRect, overlappingRectangle)) {
                    
                    if(playerVelX < 0)
                    {
                        playerGeo.translate(  overlappingRectangle.width, 0 );

                    }
                    else{
                        playerGeo.translate(  overlappingRectangle.width * -1, 0 );

                    }

                }

            }
        }


       


        player.e.moveY();

        for (Shape2D shape : map.getMapPolies()) {

            if (shape.getClass() == Polyline.class) {
                Polyline p = (Polyline) shape;
                float[] vertices = p.getTransformedVertices();
                while (Intersector.intersectSegmentRectangle(vertices[0], vertices[1], vertices[2], vertices[3],
                        playerRect)) {
                
                        playerGeo.translate(0, Math.signum(playerVelY) * -1);
                        player.e.setVelocityY(0);

    
                }
            }

        }

        for (Shape2D shape : map.getMapPolies()) {

            if (shape.getClass() == Rectangle.class) {
                Rectangle tileAsRect = (Rectangle) shape;

                if (Intersector.intersectRectangles(playerRect, tileAsRect, overlappingRectangle)) {


                    if(playerVelY < 0)
                    {
                        playerGeo.translate(  0, overlappingRectangle.height );
                        player.e.setVelocityY(0);


                    }
                    else{
                        playerGeo.translate(  0, overlappingRectangle.height * -1);
                        player.e.setVelocityY((float)(playerVelY * -0.5));



                    }
                }

            }
        }


       

    
    }

}
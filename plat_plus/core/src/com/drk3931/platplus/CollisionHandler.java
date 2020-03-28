package com.drk3931.platplus;

import java.util.Iterator;
import java.util.LinkedList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.drk3931.platplus.projectiles.PlayerProjectile;
import com.drk3931.platplus.projectiles.Projectile;

class CollisionHandler {

    private World world;
    private Map map;
    private Player player;
    private Rectangle overlappingRectangle;

    public void setWorld(World world){
        this.world = world;
        this.player = world.getPlayer();
    }


    public CollisionHandler(Map map) {
       
        this.map = map;
        
        this.overlappingRectangle = new Rectangle();
    }


    public void update(float delta) {
        

        GeometricRepresentation playerGeo = player.e.getGeoRep();
        Rectangle playerRect = (Rectangle) playerGeo.shapeRepresentation;

        float playerVelX = player.e.getVelocityX(),playerVelY = player.e.getVelocityY();


        
        Iterator<Projectile> projectileIterator = world.projectileStore.iterator();
        while(projectileIterator.hasNext()){
            Projectile p = projectileIterator.next();

            for(Character c:world.getCharacters()){
                if(Intersector.overlaps((Circle)p.asEntity().getGeoRep().shapeRepresentation,(Rectangle)c.entityRep.getGeoRep().shapeRepresentation)){
                    if(p instanceof PlayerProjectile){
                        p.onHit((Enemy)c);
                        projectileIterator.remove();
                    }
                }
            }
        }
        

              
      
        for(Character c:world.getCharacters()){
          Rectangle characterRect = (Rectangle)c.entityRep.getGeoRep().shapeRepresentation;
          if(playerRect.overlaps(characterRect) && !player.isInvincible() && c.characterState.getCurrentState() != CharacterState.State.DEAD){
              player.onKnockBack();
          }
        }

     

        

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
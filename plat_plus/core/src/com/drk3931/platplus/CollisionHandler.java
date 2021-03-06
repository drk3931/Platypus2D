package com.drk3931.platplus;

import java.util.Iterator;
import java.util.LinkedList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.drk3931.platplus.PlatPlus.GameState;
import com.drk3931.platplus.Player.DamageType;
import com.drk3931.platplus.UIHandler.UIEvent;
import com.drk3931.platplus.projectiles.PlayerProjectile;
import com.drk3931.platplus.projectiles.Projectile;

class CollisionHandler {

    private PlatPlus gameRef; 
    private World world;
    private Map map;
    private Player player;
    private Rectangle overlappingRectangle;
    private UIHandler uiHandler;
    private Rectangle winRectangle;

    public void setWinRectangle(Rectangle winRectangle){
        this.winRectangle = winRectangle;
    }

    public void setWorld(World world){
        this.world = world;
        this.player = world.getPlayer();
    }


    public CollisionHandler( PlatPlus gameRef) {
       
        this.uiHandler = gameRef.uiHandler;
        this.map = gameRef.map;
        this.overlappingRectangle = new Rectangle();
        this.gameRef = gameRef;
    }


    public void update() {
        

        GeometricRepresentation playerGeo = player.e.getGeoRep();
        Rectangle playerRect = (Rectangle) playerGeo.shapeRepresentation;


        if(playerRect.overlaps(winRectangle)){

            gameRef.setGameState(GameState.GAME_WON);
            
        }


        float playerVelX = player.e.getVelocityX(),playerVelY = player.e.getVelocityY();


        //handle projection collisions
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
        

              
        //handle 
        for(Character c:world.getCharacters()){
          Rectangle characterRect = (Rectangle)c.entityRep.getGeoRep().shapeRepresentation;
          if(playerRect.overlaps(characterRect) && c.characterState.getCurrentState() != CharacterState.State.DEAD){
              
            
              player.onDamage(DamageType.COLLISION);
              uiHandler.onEvent(UIEvent.PLAYER_DAMAGED,world.getPlayer().health);
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

            if (shape.getClass() == Rectangle.class) {
                Rectangle tileAsRect = (Rectangle) shape;

                if (Intersector.intersectRectangles(playerRect, tileAsRect, overlappingRectangle)) {
                    
                    playerGeo.translate(  Math.signum(playerVelX) * -1 * overlappingRectangle.width, 0 );

                }

            }
        }


       


        player.e.moveY();

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
                        player.e.setVelocityY((float)(playerVelY * -0.25));



                    }
                }

            }
        }


       

    
    }

}
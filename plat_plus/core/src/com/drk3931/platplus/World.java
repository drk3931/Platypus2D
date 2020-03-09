package com.drk3931.platplus;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

class World implements DrawableComponent {
    
    final public static float accelGravity = -19f;


    
    Player player;
    

    public World() {

        player = new Player();
        
       
    }

    
    public void update(float delta) {



        player.update(delta);


        //geoRep.translate(lastChangeX, lastChangeY);



    }

    @Override
    public void drawShapeRenderer(ShapeRenderer shapeRenderer) {


        player.drawShapeRenderer(shapeRenderer);
        
        /*
        for (Character c: characters) {
            c.getCharacterEntity().drawShapeRenderer(shapeRenderer);
        }

        player.drawShapeRenderer(shapeRenderer);
        */

    }

    @Override
    public void drawSpriteBatch(SpriteBatch b) {


        /*
       for (Character c: characters) {
            c.getCharacterEntity().drawSpriteBatch(b);
        }

        player.drawSpriteBatch(b);
        */


    }


  
  
}

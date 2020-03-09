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
    

    GeometricRepresentation geoRep;
    public final int speedx = 200, speedy = 200; float lastChangeX = 0, lastChangeY = 0;

    public World() {

        geoRep = new GeometricRepresentation(Color.WHITE,new Rectangle(0,128,64,64));
       
    }

    
    public void update(float delta) {

        lastChangeX = 0;
        lastChangeY = 0;


        if(Gdx.input.isKeyPressed(Keys.LEFT))
        {
            lastChangeX = delta * speedx * -1;
        }

        if(Gdx.input.isKeyPressed(Keys.RIGHT))
        {
            lastChangeX = delta * speedx;
        }

        if(Gdx.input.isKeyPressed(Keys.UP))
        {
            lastChangeY = speedy * delta;
        }

        if(Gdx.input.isKeyPressed(Keys.DOWN))
        {
            lastChangeY = speedy * delta * -1;
        }

        geoRep.translate(lastChangeX, lastChangeY);



    }

    @Override
    public void drawShapeRenderer(ShapeRenderer shapeRenderer) {

        Rectangle r = (Rectangle)geoRep.shapeRepresentation;
       
        shapeRenderer.setColor(geoRep.getColor());
        shapeRenderer.set(ShapeType.Filled);
        shapeRenderer.rect(r.x,r.y,r.width,r.height);



        
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

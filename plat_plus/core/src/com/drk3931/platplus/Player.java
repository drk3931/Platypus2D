package com.drk3931.platplus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

class Player implements DrawableComponent {

    int speedX = 200, speedY = 200;
    Entity e;

    public Player() {

        e = new Entity();
        e.setGeoRep(new GeometricRepresentation(Color.WHITE,new Rectangle(0,150,64,64)));

    }

    public void update(float delta) {

        e.setVelocityX(0);
        e.setVelocityY(0);

        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            e.setVelocityX(delta * speedX * -1);
        }

        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            e.setVelocityX(delta * speedX);
        }

        if (Gdx.input.isKeyPressed(Keys.UP)) {
            e.setVelocityY(delta * speedY);
        }

        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            e.setVelocityY(delta * speedY * -1);
        }
    }

    @Override
    public void drawShapeRenderer(ShapeRenderer shapeRenderer) {
        GeometricRepresentation geoRep = e.getGeoRep();
        Rectangle r = (Rectangle)geoRep.shapeRepresentation;
       
        shapeRenderer.setColor(geoRep.getColor());
        shapeRenderer.set(ShapeType.Filled);
        shapeRenderer.rect(r.x,r.y,r.width,r.height);

    }

    @Override
    public void drawSpriteBatch(SpriteBatch b) {
        // TODO Auto-generated method stub

    }

}
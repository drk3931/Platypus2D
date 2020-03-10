package com.drk3931.platplus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

class Player implements DrawableComponent, CameraController {

    int speedX = 333, jumpVelocity = 2000;
    Entity e;

    GravityEffect gravEffect;

    public Player() {

        e = new Entity();
        e.setGeoRep(new GeometricRepresentation(Color.WHITE, new Rectangle(0, 150, 64, 64)));
        gravEffect = new GravityEffect();
    }

    public void update(float delta) {

        e.setVelocityX(0);

        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            e.setVelocityX(delta * speedX * -1);
        }

        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            e.setVelocityX(delta * speedX);
        }

        if (Gdx.input.isKeyJustPressed(Keys.UP)) {
            e.setVelocityY(delta * jumpVelocity);
        }

        gravEffect.apply(e, delta);
    }

    @Override
    public void drawShapeRenderer(ShapeRenderer shapeRenderer) {
        GeometricRepresentation geoRep = e.getGeoRep();
        Rectangle r = (Rectangle) geoRep.shapeRepresentation;

        shapeRenderer.setColor(geoRep.getColor());
        shapeRenderer.set(ShapeType.Filled);
        shapeRenderer.rect(r.x, r.y, r.width, r.height);

    }

    @Override
    public void drawSpriteBatch(SpriteBatch b) {
        // TODO Auto-generated method stub

    }

    @Override
    public void applyToCam(Camera c) {

        
        c.position.x = e.getGeoRep().getX();
        c.position.y = e.getGeoRep().getY();
        
    }

}
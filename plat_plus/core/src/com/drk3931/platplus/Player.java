package com.drk3931.platplus;
import com.drk3931.platplus.effects.GravityEffect;
import com.drk3931.platplus.projectiles.Projectile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

class Player implements DrawableComponent, CameraController,Updateable {

    int speedX = 333, jumpVelocity = 1400;
    Entity e;

    GravityEffect gravEffect;
    Vector3 cameraUnprojected; 

    Camera camRef;

    public Player() {

        e = new Entity();
        e.setGeoRep(new GeometricRepresentation(Color.WHITE, new Rectangle(0, 150, 64, 64)));
        gravEffect = new GravityEffect();
        cameraUnprojected = new Vector3();
    }


    long lastFire = 0;

    @Override
    public void update(float delta) {

        e.setVelocityX(0);
        //e.setVelocityY(0);

        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            e.setVelocityX(delta * speedX * -1);
        }

        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            e.setVelocityX(delta * speedX);
        }

        
        if (Gdx.input.isKeyPressed(Keys.UP) && e.getVelocityY() == 0) {
            e.setVelocityY(delta * jumpVelocity);
        }
        

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            int yPos = Gdx.input.getY();
            int xPos = Gdx.input.getX();

            if(System.currentTimeMillis() - lastFire > 650)
            {

                cameraUnprojected.set(xPos, yPos, 0);
                cameraUnprojected.set(Renderer.getMousePosInGameWorld(cameraUnprojected, camRef));

            
                lastFire = System.currentTimeMillis();
                World.projectileStore.add(new Projectile(this.e, Color.BLUE, cameraUnprojected.x, cameraUnprojected.y));

            }
        
            
            //this.player.weapon.fire(xPos, Gdx.graphics.getHeight() - yPos);
        }

        /*
        if (Gdx.input.isKeyPressed(Keys.UP)) {
            e.setVelocityY(delta * 300);
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            e.setVelocityY(delta * 300 * -1);
        }*/

        gravEffect.apply(e, delta);
    }

    @Override
    public void drawShapeRenderer(ShapeRenderer shapeRenderer) {
       this.e.getGeoRep().drawShapeRenderer(shapeRenderer);
    }

    @Override
    public void drawSpriteBatch(SpriteBatch b) {
        // TODO Auto-generated method stub

    }

    @Override
    public void applyToCam(Camera c) {


        this.camRef = c;

        
        c.position.x = e.getGeoRep().getX();

        if(e.getGeoRep().getY() > Gdx.graphics.getHeight()/2)
        {
            c.position.y = e.getGeoRep().getY();
        }
        else{
            c.position.y = Gdx.graphics.getHeight()/2;
        }


        
    }

}
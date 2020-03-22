package com.drk3931.platplus;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Entity implements DrawableComponent {

    private GeometricRepresentation geoRep;

    private Vector2 velocity;

    private TextureRegion currentTextureRegion;


    public TextureRegion getCurrentTextureRegion(){
        return this.currentTextureRegion;
    }

    public void setCurrentTextureRegion(TextureRegion tex){
        this.currentTextureRegion = tex;
    }


    public float getVelocityX() {
        return velocity.x;
    }

    public float getVelocityY() {
        return velocity.y;
    }

    public Entity() {
        velocity = new Vector2();
    }

    public Entity setGeoRep(GeometricRepresentation geo) {
        this.geoRep = geo;
        return this;
    }

    public GeometricRepresentation getGeoRep() {
        return this.geoRep;
    }

    public void moveX() {
        this.geoRep.translate(this.velocity.x, 0);
    }

    public void moveY() {
        this.geoRep.translate(0, this.velocity.y);
    }

    public void setVelocityX(float vx) {
        this.velocity.x = vx;
    }

    public void setVelocityY(float vy) {
        this.velocity.y = vy;
    }

    public void setVelocity(float x, float y) {
        this.velocity.x = x;
        this.velocity.y = y;
    }

    public Vector2 getVelocity() {
        return this.velocity;
    }

    public void move(float delta) {
        this.geoRep.translate(delta * velocity.x, delta * velocity.y);
    }

    @Override
    public void drawShapeRenderer(ShapeRenderer r) {
      
        this.geoRep.drawShapeRenderer(r);

    }

    @Override
    public void drawSpriteBatch(SpriteBatch b) {
        
        
        b.draw(currentTextureRegion,geoRep.getX(),geoRep.getY(),geoRep.getWidth(),geoRep.getHeight());

    }

    
}
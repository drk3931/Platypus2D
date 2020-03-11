package com.drk3931.platplus;

import com.badlogic.gdx.math.Vector2;

public class Entity{


    private GeometricRepresentation geoRep;

    private Vector2 velocity;

    boolean markedForRemoval = false;

    void setMarkedForRemoval(){
        this.markedForRemoval = true;
    }

    public float getVelocityX()
    {
        return velocity.x;
    }

    public float getVelocityY(){
        return velocity.y;
    }

    public Entity()
    {
        velocity = new Vector2();
    }

    public Entity setGeoRep(GeometricRepresentation geo){
        this.geoRep = geo;
        return this;
    }

    public GeometricRepresentation getGeoRep()
    {
        return this.geoRep;
    }

    public void moveX(){
        this.geoRep.translate(this.velocity.x, 0);
    }

    public void moveY(){
        this.geoRep.translate(0, this.velocity.y);
    }

    public void setVelocityX(float vx)
    {
        this.velocity.x = vx;
    }   
    public void setVelocityY(float vy)
    {
        this.velocity.y = vy; 
    }

    public void setVelocity(float x, float y)
    {
        this.velocity.x = x; this.velocity.y = y;
    }
    public Vector2 getVelocity(){
        return this.velocity;
    }

    public void move(float delta){
        this.geoRep.translate(delta *velocity.x, delta * velocity.y);
    }

    
}
package com.drk3931.platplus;

public class Entity{


    private GeometricRepresentation geoRep;

    float velocityX,velocityY;

    boolean markedForRemoval = false;

    void setMarkedForRemoval(){
        this.markedForRemoval = true;
    }

    public float getVelocityX()
    {
        return this.velocityX;
    }

    public float getVelocityY(){
        return this.velocityY;
    }

    public Entity()
    {

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
        this.geoRep.translate(velocityX, 0);
    }

    public void moveY(){
        this.geoRep.translate(0, velocityY);
    }

    public void setVelocityX(float vx)
    {
        this.velocityX = vx;
    }   
    public void setVelocityY(float vy)
    {
        this.velocityY = vy; 
    }
}
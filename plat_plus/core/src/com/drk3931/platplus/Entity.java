package com.drk3931.platplus;


abstract class Entity implements Updateable{


    public GeometricRepresentation geometricRepresentation;
    public EntityStats entityStats;
    abstract public GameEvent onCollision(Entity e);
    public float xVelocity, yVelocity;



    public Entity()
    {
        this.geometricRepresentation = new GeometricRepresentation();
        this.entityStats = new EntityStats();
    }

    public GeometricRepresentation getGeometricRepresentation()
    {
        return this.geometricRepresentation;
    }

    public void setVelocity(float xVelocity, float yVelocity)
    {
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity; 
    }


    public void move(float delta)
    {
        geometricRepresentation.translate(delta * xVelocity, delta * yVelocity);        
    
    }


    



}
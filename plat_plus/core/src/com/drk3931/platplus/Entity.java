package com.drk3931.platplus;


abstract class Entity implements Updateable{


    public GeometricRepresentation geometricRepresentation;
    public EntityStats entityStats;
    abstract public void onCollision(Entity e);
    public float xVelocity, yVelocity;


    protected void loadStats(EntityStats eStats)
    {
        this.entityStats = eStats;
    }
  
    enum Identity{
        ENEMY,
        PLAYER,
        FRIEND,
        PROJECTILE
    }

    private Identity identity;


    public Entity()
    {
        this.geometricRepresentation = new GeometricRepresentation();
        this.entityStats = new EntityStats();
    }

    public Entity setIdentity(Identity ident)
    {
        this.identity = ident;
        return this;
    }

    public Identity getIdentity()
    {
        return this.identity;
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
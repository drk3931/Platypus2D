package com.drk3931.platplus;


abstract class Entity {


    protected GeometricRepresentation geometricRepresentation;
    public EntityStats entityStats;


    public GeometricRepresentation getGeometricRepresentation()
    {
        return this.geometricRepresentation;
    }
    
    public abstract void setXY(int x,int y);
    public abstract void setWH(int w,int h);
    public abstract void translate(float dx,float dy);


}
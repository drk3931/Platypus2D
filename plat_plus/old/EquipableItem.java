package com.drk3931.platplus;

abstract class EquipableItem implements DrawableComponent,Updateable
{

    protected int boundX, boundY;
    protected CharacterEntity boundEntity;
    public GeometricRepresentation geoRep;
    
    public EquipableItem(CharacterEntity c)
    {
        this.boundEntity = c;
    }

    @Override
    public void update(float delta) {
        this.boundX = this.boundEntity.geometricRepresentation.getX() + this.boundEntity.geometricRepresentation.getWidth() / 2;        
        this.boundY = this.boundEntity.geometricRepresentation.getY() + this.boundEntity.geometricRepresentation.getHeight() / 2;
    }


}
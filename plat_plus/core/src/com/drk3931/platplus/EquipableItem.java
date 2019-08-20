package com.drk3931.platplus;

class EquipableItem implements Updateable
{

    protected int boundX, boundY;
    protected CharacterEntity boundEntity;
    
    public EquipableItem(CharacterEntity c)
    {
        this.boundEntity = c;
    }

    @Override
    public void update(float delta) {
        this.boundX = this.boundEntity.getX() + this.boundEntity.getWidth() / 2;        
        this.boundY = this.boundEntity.getY() + this.boundEntity.getHeight() / 2;

  

    }


}
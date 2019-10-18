package com.drk3931.platplus;

public abstract class Character implements Updateable{

    private boolean markedForRemoval = false;

    public void setMarkedForRemoval() {
        this.markedForRemoval = true;
    }

    public boolean isMarkedForRemoval() {
        return this.markedForRemoval;
    }

    public abstract CharacterEntity getCharacterEntity();

}
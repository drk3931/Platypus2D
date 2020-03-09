package com.drk3931.platplus;

public abstract class EntityStats implements Updateable{

    enum STATE {
        NORMAL, DEAD, SPECIAL, DAMAGED
    };

    protected STATE entityState;

    public STATE getState() {
        return entityState;
    }

    private float stateTimer = 0; 

    public void setState(STATE s)
    {
        this.entityState = s;
    }

    public EntityStats() {

    }

    public void update(float delta)
    {
        this.stateTimer+=delta;
    }



 

}
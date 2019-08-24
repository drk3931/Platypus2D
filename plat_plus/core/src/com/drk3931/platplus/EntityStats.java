package com.drk3931.platplus;

public class EntityStats {

    enum STATE {
        ALIVE, DEAD, SPECIAL
    };

    protected STATE entityState;

    public STATE getState() {
        return entityState;
    }

    public EntityStats() {

    }

 

}
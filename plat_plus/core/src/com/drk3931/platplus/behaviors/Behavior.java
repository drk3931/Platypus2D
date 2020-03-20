package com.drk3931.platplus.behaviors;
import com.drk3931.platplus.Entity;
import com.drk3931.platplus.Updateable;

abstract public class Behavior implements Updateable{
    private Entity controllingCharacter;

    public Behavior(Entity controllingCharacter)
    {
        this.controllingCharacter = controllingCharacter;
    }
}
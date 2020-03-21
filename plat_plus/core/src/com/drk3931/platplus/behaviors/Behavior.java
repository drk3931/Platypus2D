package com.drk3931.platplus.behaviors;
import com.drk3931.platplus.Character;
import com.drk3931.platplus.Entity;
import com.drk3931.platplus.Updateable;

abstract public class Behavior implements Updateable{
    private Character controllingCharacter;

    public Behavior(Character controllingCharacter)
    {
        this.controllingCharacter = controllingCharacter;
    }
}
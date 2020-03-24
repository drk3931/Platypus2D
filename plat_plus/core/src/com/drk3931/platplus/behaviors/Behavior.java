package com.drk3931.platplus.behaviors;
import com.drk3931.platplus.Character;
import com.drk3931.platplus.Entity;
import com.drk3931.platplus.GeometricRepresentation;
import com.drk3931.platplus.Updateable;

abstract public class Behavior implements Updateable{
    protected Character controllingCharacter;
    protected GeometricRepresentation controllingGeo;

    public Behavior(Character controllingCharacter)
    {
        this.controllingCharacter = controllingCharacter;
        this.controllingGeo = controllingCharacter.entityRep.getGeoRep();
    }
}
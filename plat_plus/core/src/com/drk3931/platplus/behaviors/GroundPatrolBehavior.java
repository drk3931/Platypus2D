package com.drk3931.platplus.behaviors;

import com.drk3931.platplus.Character;
import com.drk3931.platplus.GeometricRepresentation;

public class GroundPatrolBehavior extends Behavior {

    public GroundPatrolBehavior(Character controllingCharacter) {
        super(controllingCharacter);
        this.controllingGeo = controllingCharacter.entityRep.getGeoRep();

    }

    public void setLimits(int left, int right,int groundPatrolSpeed){
        leftLimit = controllingCharacter.entityRep.getGeoRep().getCenterX() + left * -1;
        rightLimit = controllingCharacter.entityRep.getGeoRep().getCenterX() + right;

        this.groundPatrolspeed = groundPatrolSpeed;


        int initialDirection = Math.random() > 0.5? -1: 1;

        this.controllingCharacter.entityRep.setVelocity(groundPatrolspeed * initialDirection, 0);


    }

    int groundPatrolspeed;
    int leftLimit;
    int rightLimit;

    @Override
    public void update(float delta) {




        if(controllingGeo.getX() < leftLimit){

            controllingCharacter.entityRep.setVelocityX(groundPatrolspeed );
            controllingGeo.setPosition(leftLimit + 1, controllingGeo.getY());
        }

        if(controllingGeo.getX() + controllingGeo.getWidth() > rightLimit){
            controllingCharacter.entityRep.setVelocityX(groundPatrolspeed * -1);
            controllingGeo.setPosition(rightLimit - controllingGeo.getWidth() - 1, controllingGeo.getY());

        }

        

        controllingCharacter.entityRep.move(delta);


    }

    
    
}
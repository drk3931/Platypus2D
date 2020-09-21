package com.drk3931.platplus.behaviors;

import com.drk3931.platplus.Character;
import com.drk3931.platplus.GeometricRepresentation;

public class GroundPatrolBehavior extends Behavior {

    final int PATROL_LEFT_LIMIT = 200, PATROL_RIGHT_LIMIT = 200, PATROL_SPEED = 75; 

    public GroundPatrolBehavior(Character controllingCharacter) {
        super(controllingCharacter);
        this.controllingGeo = controllingCharacter.entityRep.getGeoRep();

        setLimits();
        

    }

    public void setLimits(){
        leftLimit = controllingCharacter.entityRep.getGeoRep().getCenterX() + PATROL_LEFT_LIMIT * -1;
        rightLimit = controllingCharacter.entityRep.getGeoRep().getCenterX() + PATROL_RIGHT_LIMIT;

        this.groundPatrolspeed = PATROL_SPEED;


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
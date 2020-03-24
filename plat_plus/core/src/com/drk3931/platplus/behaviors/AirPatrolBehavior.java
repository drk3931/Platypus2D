package com.drk3931.platplus.behaviors;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.drk3931.platplus.Character;
import com.drk3931.platplus.Entity;
import com.drk3931.platplus.GeometricRepresentation;

public class AirPatrolBehavior extends Behavior {

    Circle moveBoundry;
    Circle innerCircleBoundry;

    Vector2 currentTarget;
    Vector2 vectorToTarget;
    int dartVelocity = 100;

    public AirPatrolBehavior(Character controllingCharacter) {
        super(controllingCharacter);
        moveBoundry = new Circle(controllingGeo.getCenterX(), controllingGeo.getCenterY(), 200);
        vectorToTarget = new Vector2();
        currentTarget = new Vector2();

        innerCircleBoundry = new Circle(controllingGeo.getCenterX(), controllingGeo.getCenterY(),20);

    
        setNextTarget();

    }


    boolean movingToCenter = false;

    @Override
    public void update(float delta) {
        
        controllingCharacter.entityRep.move(delta);


        if(!moveBoundry.contains(controllingGeo.getCenterX(), controllingGeo.getCenterY())){
            gotToCenter();
            movingToCenter = true;
        }

        if(movingToCenter && innerCircleBoundry.contains(controllingGeo.getCenterX(), controllingGeo.getCenterY())){
            setNextTarget();
            movingToCenter = false;
        }

    }

    private void setNextTarget(){
        float angle = (float)(Math.random()*Math.PI*2);
        currentTarget.set((float)Math.cos(angle)*moveBoundry.radius,(float)Math.sin(angle)*moveBoundry.radius);
        currentTarget.add(new Vector2(moveBoundry.x,moveBoundry.y));

        vectorToTarget = currentTarget.cpy().sub(new Vector2(moveBoundry.x,moveBoundry.y)).nor().scl(dartVelocity);

        controllingCharacter.entityRep.setVelocity(vectorToTarget.x , vectorToTarget.y );

    }

    private void gotToCenter(){
        currentTarget.set(moveBoundry.x,moveBoundry.y);

        vectorToTarget = new Vector2(currentTarget).sub(controllingGeo.getCenterX(), controllingGeo.getCenterY()).nor().scl(dartVelocity);

        controllingCharacter.entityRep.setVelocity(vectorToTarget.x , vectorToTarget.y );

    }

    
    
}
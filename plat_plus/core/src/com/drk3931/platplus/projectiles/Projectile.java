package com.drk3931.platplus.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.drk3931.platplus.Character;
import com.drk3931.platplus.DrawableComponent;
import com.drk3931.platplus.Entity;
import com.drk3931.platplus.GeometricRepresentation;
import com.drk3931.platplus.Updateable;

public abstract class Projectile implements DrawableComponent, Updateable {

    Entity origin;
    Entity projectileRep;

    public Entity asEntity(){
        return this.projectileRep;
    }

    public Projectile(Entity origin, Color color, float xTarg,float yTarg)
    {
        this.origin = origin;

        int boundX = origin.getGeoRep().getX() + origin.getGeoRep().getWidth()/2;
        int boundY = origin.getGeoRep().getY() + origin.getGeoRep().getHeight()/2;
        Circle c = new Circle(boundX,boundY,15);

      
        projectileRep = new Entity();
        projectileRep.setGeoRep(new GeometricRepresentation(color, c));
        projectileRep.getVelocity().set(xTarg - boundX,yTarg - boundY).nor().scl(777);




    }

    @Override
    public void drawShapeRenderer(ShapeRenderer r) {

        projectileRep.getGeoRep().drawShapeRenderer(r);

    }

    @Override
    public void drawSpriteBatch(SpriteBatch b) {

    }

    @Override
    public void update(float delta) {
       
        projectileRep.move(delta);

    }

    public abstract void onHit(Character c);
    
    //may also add an on hit for entities 



}
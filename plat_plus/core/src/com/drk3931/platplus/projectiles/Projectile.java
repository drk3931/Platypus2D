package com.drk3931.platplus.projectiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.drk3931.platplus.DrawableComponent;
import com.drk3931.platplus.Entity;
import com.drk3931.platplus.GeometricRepresentation;
import com.drk3931.platplus.Updateable;

public class Projectile implements DrawableComponent, Updateable {

    Entity origin;
    Entity projectileRep;
    Vector2 velocity;

    public Projectile(Entity origin, Color color, int targX, int targY)
    {
        this.origin = origin;

        int boundX = origin.getGeoRep().getX() + origin.getGeoRep().getWidth()/2;
        int boundY = origin.getGeoRep().getY() + origin.getGeoRep().getHeight()/2;
        Circle c = new Circle(boundX,boundY,50);

      
        projectileRep = new Entity();
        projectileRep.setGeoRep(new GeometricRepresentation(color, c));



        int yComputed = targY - boundY;
        int xComputed = targX - boundX;

        velocity = new Vector2(xComputed,yComputed).nor().setLength(100);



    }

    @Override
    public void drawShapeRenderer(ShapeRenderer r) {

        Circle c = (Circle)projectileRep.getGeoRep().shapeRepresentation;
        r.circle(c.x, c.y, c.radius);



    }

    @Override
    public void drawSpriteBatch(SpriteBatch b) {

    }

    @Override
    public void update(float delta) {
        // TODO Auto-generated method stub
        projectileRep.getGeoRep().translate(delta * velocity.x, delta * velocity.y);
        

    }


}
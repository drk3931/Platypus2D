package com.drk3931.platplus.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.drk3931.platplus.Character;
import com.drk3931.platplus.DrawableComponent;
import com.drk3931.platplus.Entity;
import com.drk3931.platplus.GeometricRepresentation;
import com.drk3931.platplus.Updateable;
import com.drk3931.platplus.GameLoader;

public abstract class Projectile implements DrawableComponent, Updateable {

    Entity origin;
    Entity projectileRep;
    TextureRegion tex;
    private double rotation = 0; 

    public Entity asEntity() {
        return this.projectileRep;
    }

    public Projectile(Entity origin, float xTarg, float yTarg) {
        this.origin = origin;

        tex = GameLoader.getTexRegion("arrow.png");
        tex.setRegionWidth(15);
        tex.setRegionHeight(15);


        int boundX = origin.getGeoRep().getX() + origin.getGeoRep().getWidth() / 2;
        int boundY = origin.getGeoRep().getY() + origin.getGeoRep().getHeight() / 2;

        rotation = Math.atan2(yTarg - boundY, xTarg - boundX) * (180.0d/ Math.PI);

    


        projectileRep = new Entity();
        projectileRep.setGeoRep(new GeometricRepresentation(Color.CLEAR, new Circle(boundX, boundY, 15)));
        projectileRep.getVelocity().set(xTarg - boundX, yTarg - boundY).nor().scl(777);

    }

    @Override
    public void drawShapeRenderer(ShapeRenderer r) {

        projectileRep.getGeoRep().drawShapeRenderer(r);

    }

    @Override
    public void drawSpriteBatch(SpriteBatch b) {

        GeometricRepresentation g = projectileRep.getGeoRep();
        b.draw(tex, (float)g.getX(), (float)g.getY(), 0,0, 50, 20, 1, 1, (float)rotation);

    }

    @Override
    public void update(float delta) {

        projectileRep.move(delta);

    }

    public abstract void onHit(Character c);

    // may also add an on hit for entities

}
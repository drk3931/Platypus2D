package com.drk3931.platplus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Character implements DrawableComponent, Updateable {

    private Entity entityRep;


    public Character(){
        this.entityRep = new Entity();
    }

    public void setupCharacter(float x, float y, float w, float h)
    {
        entityRep.setGeoRep(new GeometricRepresentation(Color.RED, new Rectangle(x,y,w,h)));

    }


    @Override
    public void drawShapeRenderer(ShapeRenderer shapeRenderer) {
        this.entityRep.getGeoRep().drawShapeRenderer(shapeRenderer);

    }

    @Override
    public void drawSpriteBatch(SpriteBatch b) {

    }

    @Override
    public void update(float delta) {
        // TODO Auto-generated method stub

    }

}
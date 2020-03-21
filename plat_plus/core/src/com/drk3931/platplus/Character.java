package com.drk3931.platplus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public abstract class Character implements DrawableComponent, Updateable {

    /*
        A character has an effect on the player, i.e damaging the player
        It has a state
    */

    private Entity entityRep;
    private Player playerRef;
    public CharacterState characterState;


    public Animation<TextureRegion> movingAnimation;
    public Animation<TextureRegion> deathAnimation;
    public Animation<TextureRegion> alertAnimation;
    public Animation<TextureRegion> attackAnimation;
    public Animation<TextureRegion> defenseAnimation;


    
    public void setEntityRep(float x,float y,float w,float h){
        this.entityRep = new Entity();
        entityRep.setGeoRep(new GeometricRepresentation(Color.WHITE, new Rectangle(x,y,w,h)));
    }


    public Character(Player player, CharacterState cState){
        this.entityRep = new Entity();
        this.playerRef = player;
        this.characterState = cState;
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
        
        this.characterState.update(delta);
        this.characterState.getCurrentBehavior().update(delta);

    }

    public abstract void spawnProjectile();

}
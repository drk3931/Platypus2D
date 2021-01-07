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

    public Entity entityRep;
    private Player playerRef;
    public CharacterState characterState;





    
    public void setEntityRep(float x,float y,float w,float h){
        this.entityRep = new Entity();
        entityRep.setGeoRep(new GeometricRepresentation(Color.WHITE, new Rectangle(x,y,w,h)));
    }


    public Character(Player player){
        this.entityRep = new Entity();
        this.playerRef = player;
        this.characterState = new CharacterState(this);
    }

    public void setCharacterState(CharacterState c){
        this.characterState = c;
    }


    @Override
    public void drawShapeRenderer(ShapeRenderer shapeRenderer) {
       
        this.entityRep.drawShapeRenderer(shapeRenderer);

    }

    @Override
    public void drawSpriteBatch(SpriteBatch b) {
      
        this.entityRep.drawSpriteBatch(b);
    }

    @Override
    public void update(float delta) {
        
        this.characterState.update(delta);
        this.entityRep.setCurrentTextureRegion(this.characterState.getCurrentRegion());
        this.entityRep.setTint(characterState.getTint());


    }

    //only the character behavior object will spawn projectile
    protected abstract void spawnProjectile();

}
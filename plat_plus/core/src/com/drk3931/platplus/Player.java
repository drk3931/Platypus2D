package com.drk3931.platplus;

import com.drk3931.platplus.CharacterState.State;
import com.drk3931.platplus.PlatPlus.GameState;
import com.drk3931.platplus.effects.GravityEffect;
import com.drk3931.platplus.projectiles.PlayerProjectile;
import com.drk3931.platplus.projectiles.Projectile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Player implements DrawableComponent, Updateable {

    private PlayerState currentState;
    private GravityEffect gravEffect;
    private AnimationHandler walkAnimation;
    private AnimationHandler stillAnimation;
    private AnimationHandler currentAnimation;

    // these allow us to control the camera and get world coordinates of where the
    // user clicks for projectile firing
    private Vector3 cameraUnprojected;
    private Camera camRef;
    private Color playerTint;

    int speedX = 250, jumpVelocity = 1650, health = 100, lastHealth = 100, 
        collisionDamage = 10;

    float damageTime = 0.75f, lastDamaged = 0, lastFire = 0, fireRate = 0.50f, sprintRate = 1.5f;
    boolean controlLocked = false, canFire = true;

    public Entity e;

    public enum PlayerState {
        DEFAULT, DEAD, DAMAGED
    }

    public Player(float x, float y) {

        e = new Entity();
        e.setGeoRep(new GeometricRepresentation(Color.ORANGE, new Rectangle(x, y, 64, 128)));
        gravEffect = new GravityEffect(e);
        cameraUnprojected = new Vector3();
        GameLoader.FLIP_TEXTURE_ON_GENERATE = true;


        stillAnimation = new AnimationHandler(GameLoader.genAnimationRange("player.png", 7,4, 0.50f,1,4),true);
        walkAnimation = new AnimationHandler(GameLoader.genAnimationRange("player.png", 7,4, 0.0650f,2,4),true);
        currentAnimation = stillAnimation;
        
        GameLoader.FLIP_TEXTURE_ON_GENERATE = false;

        currentState = PlayerState.DEFAULT;
        playerTint = Color.WHITE;

    }

    public boolean LR = false; 

    private void controlPlayer(float delta) {


        
        if (isControlLocked()) {
            return;
        }


        LR = true; 

        if (!Gdx.input.isKeyPressed(Keys.A) && !Gdx.input.isKeyPressed(Keys.D)) {

            LR = false; 

        }
       

        e.setVelocityX(0);


        if (Gdx.input.isKeyPressed(Keys.A) && LR) {
            
            e.setVelocityX(delta * speedX * -1);
            walkAnimation.incrementTime(delta);
            currentAnimation = walkAnimation;

        }
        else if (Gdx.input.isKeyPressed(Keys.D) && LR) {

            e.setVelocityX(delta * speedX);
            walkAnimation.incrementTime(delta);
            currentAnimation = walkAnimation;


        }
        else{
            stillAnimation.incrementTime(delta);
            currentAnimation = stillAnimation;
        }
        
  


        if (Gdx.input.isKeyPressed(Keys.W) && canJump()) {
            e.setVelocityY(delta * jumpVelocity);
        }

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            int yPos = Gdx.input.getY();
            int xPos = Gdx.input.getX();

            if (canFire) {

                cameraUnprojected.set(xPos, yPos, 0);
                cameraUnprojected.set(camRef.unproject(cameraUnprojected));

                lastFire = 0;
                canFire = false;

                World.projectileStore.add(new PlayerProjectile(this, cameraUnprojected.x, cameraUnprojected.y));

            }

        }
    }

    private void updateTimers(float delta){
        if(!canFire){
            lastFire += delta;
        }

        if(lastFire > fireRate){
            lastFire = 0;
            canFire = true;
        }


        if (currentState == PlayerState.DAMAGED) {
           
            lastDamaged += delta;
  

        }
        if(lastDamaged > damageTime){
            lastDamaged = 0;
            this.currentState = PlayerState.DEFAULT;
        }

    }

    @Override
    public void update(float delta) {


        if (currentState == PlayerState.DEAD) {
            return;
        }

        playerTint = Color.WHITE;

        updateTimers(delta);

     

        if (health <= 0) {
            currentState = PlayerState.DEAD;
        }


        if (currentState == PlayerState.DAMAGED) {

            playerTint = Color.RED;

        } else {
            playerTint = Color.WHITE;

        }

        e.setCurrentTextureRegion(currentAnimation.getCurrentRegion());

        // this needs to be called before gravEffect is applied
        controlPlayer(delta);

        gravEffect.apply(delta);

        lastHealth = health;

    }

    @Override
    public void drawShapeRenderer(ShapeRenderer shapeRenderer) {
        // this.e.getGeoRep().drawShapeRenderer(shapeRenderer);
    }

    @Override
    public void drawSpriteBatch(SpriteBatch b) {

        // animationHandler.draw(b);
        e.setTint(playerTint);
        this.e.drawSpriteBatch(b);

    }

  

    enum DamageType {
        COLLISION, PROJECTILE
    }

    public void onDamage(DamageType dType) {

        if (!(currentState == PlayerState.DAMAGED)) {

            if (dType == DamageType.COLLISION) {
                this.health-=collisionDamage;
                this.currentState = PlayerState.DAMAGED;
            }

        }

    }

    public boolean isControlLocked() {
        return controlLocked;
    }

    public PlayerState getCurrentState() {
        return this.currentState;
    }

    private boolean canJump() {

        return e.getVelocityY() == 0;
    }

    public void setCamRef(Camera c){
        this.camRef = c;
    }

}
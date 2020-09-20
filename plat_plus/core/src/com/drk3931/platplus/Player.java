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

public class Player implements DrawableComponent, CameraController, Updateable {

    private PlayerState currentState;
    private GravityEffect gravEffect;
    private AnimationHandler animationHandler;

    // these allow us to control the camera and get world coordinates of where the
    // user clicks for projectile firing
    private Vector3 cameraUnprojected;
    private Camera camRef;
    private Color playerTint;

    int speedX = 333, jumpVelocity = 1000, health = 100, lastHealth = 100, 
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
        gravEffect = new GravityEffect();
        cameraUnprojected = new Vector3();
        GameLoader.FLIP_TEXTURE_ON_GENERATE = true;
        animationHandler = new AnimationHandler(GameLoader.genAnimation("playerWalk.png", 6,5, 0.025f),true);
        GameLoader.FLIP_TEXTURE_ON_GENERATE = false;

        currentState = PlayerState.DEFAULT;
        playerTint = Color.WHITE;

    }

    private void controlPlayer(float delta) {

        if (isControlLocked()) {
            return;
        }

        e.setVelocityX(0);

     
        boolean sprinting = false;

        if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) && !canJump()){
            sprinting = true;
            animationHandler.setTimeScale(1.5f);

        }
        else{
            e.setVelocityX(e.getVelocityX());
            animationHandler.setTimeScale(1.0f);
        }


        if (Gdx.input.isKeyPressed(Keys.A)) {
            e.setVelocityX(delta * speedX * -1);
            animationHandler.incrementTime(delta);

        }
        else if (Gdx.input.isKeyPressed(Keys.D)) {
            e.setVelocityX(delta * speedX);
            animationHandler.incrementTime(delta);


        }

        if(sprinting){
            e.setVelocityX(e.getVelocityX() * sprintRate);
        }
      



        if (Gdx.input.isKeyPressed(Keys.W) && canJump()) {
            e.setVelocityY(delta * jumpVelocity);
        }

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            int yPos = Gdx.input.getY();
            int xPos = Gdx.input.getX();

            if (canFire) {

                cameraUnprojected.set(xPos, yPos, 0);
                cameraUnprojected.set(Renderer.getMousePosInGameWorld(cameraUnprojected, camRef));

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

        e.setCurrentTextureRegion(animationHandler.getCurrentRegion());

        // this needs to be called before gravEffect is applied
        controlPlayer(delta);

        gravEffect.apply(e);

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

    @Override
    public void applyToCam(Camera c) {

        this.camRef = c;

        if (e.getGeoRep().getX() > Gdx.graphics.getWidth() / 2) {
            c.position.x = e.getGeoRep().getX();
        } else {
            c.position.x = Gdx.graphics.getWidth() / 2;
        }

        if (e.getGeoRep().getY() > Gdx.graphics.getHeight() / 2) {
            c.position.y = e.getGeoRep().getY();
        } else {
            c.position.y = Gdx.graphics.getHeight() / 2;
        }

    }

    enum DamageType {
        COLLISION, PROJECTILE
    }

    public void onDamage(DamageType dType, float delta) {

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

}
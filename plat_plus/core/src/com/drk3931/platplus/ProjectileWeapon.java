package com.drk3931.platplus;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ProjectileWeapon extends EquipableItem {

    Stack<Projectile> mag;
    LinkedList<Projectile> poppedProjectiles;
    float timeSinceLastFire = 0;
    final float fireRate = 0.09f;

    public ProjectileWeapon(CharacterEntity c) {

        super(c);
        this.mag = new Stack<Projectile>();
        poppedProjectiles = new LinkedList<Projectile>();
        reload(1000);

    }

    public void reload(int numProj) {
        for (int i = 0; i < numProj; i++) {
            this.mag.push(new Projectile(this));
        }
    }

    public void fire(int xDir, int yDir) {

        if (timeSinceLastFire < fireRate) {
            return;
        }
        try {

            Projectile p = this.mag.pop();


            int yComputed = yDir - boundY;
            int xComputed = xDir - boundX;

            p.setTrajectory(boundX, boundY, (int) xComputed, (int) yComputed);

            this.poppedProjectiles.add(p);
            timeSinceLastFire = 0;

        } catch (Exception e) {
            Gdx.app.log("PROJECTILE WEAPON", "Out of ammo!");
        }
    }

    @Override
    public void drawShapeRenderer(ShapeRenderer r) {

        Iterator<Projectile> i = poppedProjectiles.iterator();

        while (i.hasNext()) {
            Projectile p = (Projectile) i.next();
            p.drawShapeRenderer(r);

        }

    }

    @Override
    public void update(float delta) {
        super.update(delta);
        this.timeSinceLastFire += delta;

        Iterator<Projectile> i = poppedProjectiles.iterator();
        while (i.hasNext()) {
            Projectile p = (Projectile) i.next();
            p.update(delta);

        }

    }

    @Override
    public void drawSpriteBatch(SpriteBatch b) {

    }

}

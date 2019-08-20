package com.drk3931.platplus;

import java.util.LinkedList;
import java.util.Stack;

import com.badlogic.gdx.Gdx;

class ProjectileWeapon extends EquipableItem{

    Stack<Projectile> mag;

    public ProjectileWeapon(int x, int y) {

        super(x,y);
        this.mag = new Stack<Projectile>();

    }

    public void reload(int numProj) {
        for (int i = 0; i < numProj; i++) {
            this.mag.push(new Projectile(super.boundX , super.boundY));
        }
    }

    public void fire(int xDir, int yDir) {
        try {
            Projectile p = this.mag.pop();
            p.xVelocity = xDir;
            p.yVelocity = yDir;
        } catch (Exception e) {
            Gdx.app.log("PROJECTILE WEAPON", "Out of ammo!");
        }
    }

}

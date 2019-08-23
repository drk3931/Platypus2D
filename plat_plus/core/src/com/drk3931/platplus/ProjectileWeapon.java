package com.drk3931.platplus;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class ProjectileWeapon extends EquipableItem {

    Stack<Projectile> mag;
    LinkedList<Projectile> poppedProjectiles;
    float timeSinceLastFire = 0;
    final float fireRate =  1;

    public ProjectileWeapon(CharacterEntity c) {

        super(c);
        this.mag = new Stack<Projectile>();
        poppedProjectiles = new LinkedList<Projectile>();
        reload(50000);

    }

    public void reload(int numProj) {
        for (int i = 0; i < numProj; i++) {
            this.mag.push(new Projectile());
        }
    }

    public void fire(int xDir, int yDir) {

        if(timeSinceLastFire < fireRate)
        {
            return;
        }
        try {



            Projectile p = this.mag.pop();


            int xComputed = xDir - Gdx.graphics.getWidth()/2;
            int yComputed = yDir - (Gdx.graphics.getHeight()/2);

    
            p.setTrajectory(boundX, boundY, (int)xComputed, (int)yComputed);
            
            this.poppedProjectiles.add(p);
            timeSinceLastFire = 0;

        } catch (Exception e) {
            Gdx.app.log("PROJECTILE WEAPON", "Out of ammo!");
        }
    }

    @Override
    public void drawShapeRenderer(ShapeRenderer r) {

        r.set(ShapeType.Filled);
        Iterator i = poppedProjectiles.iterator();

        while(i.hasNext())
        {
            Projectile p = (Projectile)i.next();
            p.drawShapeRenderer(r);

        }
        

    }

    @Override
    public void update(float delta)
    {
        super.update(delta);
        this.timeSinceLastFire+=delta;

        Iterator i = poppedProjectiles.iterator();
        while(i.hasNext())
        {
            Projectile p = (Projectile)i.next();
            p.update(delta);

        }
        
    }

    @Override
    public void drawSpriteBatch(SpriteBatch b) {
		
	}

}

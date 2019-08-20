package com.drk3931.platplus;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class ProjectileWeapon extends EquipableItem implements DrawableComponent,Updateable{

    Stack<Projectile> mag;
    LinkedList<Projectile> poppedProjectiles;

    public ProjectileWeapon(CharacterEntity c) {

        super(c);
        this.mag = new Stack<Projectile>();
        poppedProjectiles = new LinkedList<Projectile>();
        reload(25);

    }

    public void reload(int numProj) {
        for (int i = 0; i < numProj; i++) {
            this.mag.push(new Projectile());
        }
    }

    public void fire(int xDir, int yDir) {
        try {
            Projectile p = this.mag.pop();


            int xComputed = xDir - boundX;
            int yComputed =(Gdx.graphics.getHeight() - yDir) - boundY;

            p.setTrajectory(boundX, boundY, xComputed, yComputed);
            
            this.poppedProjectiles.add(p);

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
            r.circle(p.circleRep.x, p.circleRep.y, p.radius);

        }
        

    }

    @Override
    public void update(float delta)
    {
        super.update(delta);
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

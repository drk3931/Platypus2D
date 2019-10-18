package com.drk3931.platplus;

public class ProjectileStats extends EntityStats {
    public int radius = 15;
    public int velScale = 500;
    public int damage = 10;

    public ProjectileStats() {
        super.entityState = STATE.SPECIAL;
    }

}
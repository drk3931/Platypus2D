package com.drk3931.platplus;

public class CharacterStats extends EntityStats {
    private int health;
    private int maxHealth;

    @Override
    public void update(float delta) {

    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int h) {
        this.health = h;
    }

    public void setMaxHealth(int h) {
        this.maxHealth = h;
    }

    public void modHealth(int amount) {
        if (entityState == STATE.SPECIAL) {
            return;
        }

        this.health += amount;

        if (health > maxHealth) {
            health = maxHealth;
        }

        if (health < 0) {
            health = 0;
        }

    }

}
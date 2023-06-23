package com.mygdx.game.enemy;

import com.badlogic.gdx.Gdx;

public class Mirror extends Enemy {
    State state;
    private float pastHealth;

    public Mirror(float positionX, float positionY, float speedX, float speedY, float health, String bulletImg, 
    String bulletType, String sprite){
        super(positionX, positionY, speedX, speedY, health, bulletImg, bulletType, sprite);

        float width = Gdx.graphics.getWidth();
        sizeX = width / 10;
        sizeY = width / 10;

        pastHealth = health;

        state = new ChargeShootState(this);
        bulletPool.setCoolDown(100000);
        damage = 300;
    }

    @Override
    protected void shot() {
        // the delta time inside the BulletPool.render inhibits the one time shot
        if(pastHealth > health){
            bulletPool.setCoolDown(0);
            state.shoot();
            System.out.println("Shooting");
            pastHealth = health;
        }
        bulletPool.setCoolDown(100000);
        state.shoot();
    }
}

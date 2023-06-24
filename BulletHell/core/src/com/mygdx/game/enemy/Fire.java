package com.mygdx.game.enemy;

import com.badlogic.gdx.Gdx;

public class Fire extends Enemy {
    State state;

    public Fire(float positionX, float positionY, float speedX, float speedY, float health, String bulletImg, 
    String bulletType, String sprite) {
        super(positionX, positionY, speedX, speedY, health, bulletImg, bulletType, sprite);
        float width = Gdx.graphics.getWidth();

        sizeX = width / 25;
        sizeY = width / 25;

        state = new ShootState(this);
        // change the shooting frequency
        bulletPool.setCoolDown(0.08f);
    }

    public void changeState(State state){
        this.state = state;
    }

    @Override
    protected void shot() {
        state.shoot();
    }

    @Override
    protected void renderVariations() {
        if (!(state instanceof ShootState)){
            batch.draw(texture, positionX, positionY, sizeX / 2, sizeY / 2, sizeX,
            sizeY, 1f, 1f, 0, 0, 0, texture.getWidth(),
            texture.getHeight(), false, false);
        }
        else
            super.renderVariations();
    }
}

package com.mygdx.game.enemy;

import com.badlogic.gdx.Gdx;

public class GiantFace extends Enemy {
    private State state;

    public GiantFace(float positionX, float positionY, float speedX, float speedY, float health, String bulletImg, 
    String bulletType, String sprite){
        super(positionX, positionY, speedX, speedY, health, bulletImg, bulletType, sprite);
        float width = Gdx.graphics.getWidth();

        sizeX = width / 13;
        sizeY = width / 13;

        damage = 300;
        state = new ChargeShootState(this);
    }

    public void changeState(State context) {
        this.state = context;
    }

    @Override
    protected void shot() {
        state.shoot();
    }
}

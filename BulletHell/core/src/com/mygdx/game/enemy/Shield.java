package com.mygdx.game.enemy;

import com.badlogic.gdx.Gdx;

public class Shield extends Enemy {
    public Shield(float positionX, float positionY, float speedX, float speedY, float health, String bulletImg, 
    String bulletType, String sprite){
        super(positionX, positionY, speedX, speedY, health, bulletImg, bulletType, sprite);
        float width = Gdx.graphics.getWidth();

        sizeX = width / 15;
        sizeY = width / 15;
    }

    @Override
    protected void shot() {
        return;
    }
}

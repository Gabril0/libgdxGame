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

        this.positionX = random.nextInt(Math.round(width - sizeX));
        this.positionY = random.nextInt(Math.round(Gdx.graphics.getHeight() - sizeY));

        state = new ShootState(this);
    }

    public void changeState(State state){
        this.state = state;
    }

    @Override
    protected void shot() {
        state.shoot();
    }
}

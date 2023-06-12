package com.mygdx.game.enemy;

import com.badlogic.gdx.Gdx;

public class Bull extends Enemy {
    // needs another render to take off the bullets

    public Bull(float positionX, float positionY, float speedX, float speedY, float health, String bulletImg, 
    String bulletType, String sprite) {
        super(positionX, positionY, speedX, speedY, health, bulletImg, bulletType, sprite);
        float width = Gdx.graphics.getWidth();

        // sets the size of the sprite on screen
        sizeX = width/15;
        sizeY = width/15;
    }

    // @Override
    // public void render(float playerCenterX, float playerCenterY) {
    //     this.playerCenterX = playerCenterX;
    //     this.playerCenterY = playerCenterY;


    // }

    @Override
    protected void move(float moveDuration, float stationaryTime){
        float deltaTime = Gdx.graphics.getDeltaTime();
        elapsedTime += deltaTime;
        if (elapsedTime >= lastMoved + moveDuration) {
            // checks the direction where the player is headed
            float directionX = Math.signum(playerCenterX - positionX);
            float directionY = Math.signum(playerCenterY - positionY);   
            if (lock == 1) {
                // follows the player
                positionX += speedX * deltaTime * directionX;
                positionY += speedY * deltaTime * directionY;
            }
        }
    }
}

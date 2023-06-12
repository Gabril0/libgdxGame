package com.mygdx.game.enemy;

import com.badlogic.gdx.Gdx;

public class Bull extends Enemy {
    // needs another render to take off the bullets

    // locks the player position so the bull can run after him
    // needs to be global, so it does not update with the render method unless needed
    private float lockedPlayerCenterX = 0;
    private float lockedPlayerCenterY = 0;

    public Bull(float positionX, float positionY, float speedX, float speedY, float health, String bulletImg, 
    String bulletType, String sprite) {
        super(positionX, positionY, speedX, speedY, health, bulletImg, bulletType, sprite);
        float width = Gdx.graphics.getWidth();

        this.positionX = positionX + (sizeX / 2);
        this.positionY = positionY + (sizeY / 2);
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
            if (lock == 1) {
                // checks the direction where the player is headed
                lockedPlayerCenterX = Math.signum(playerCenterX - positionX);
                lockedPlayerCenterY = Math.signum(playerCenterY - positionY);
                lock = 0;
            }
            else {
                // follows the player
                // THE BULL IS NOT FOLLOWING THE PLAYER CORRECTLY, it follows the top right corner of the player
                positionX += speedX * deltaTime * lockedPlayerCenterX;
                positionY += speedY * deltaTime * lockedPlayerCenterY;
                // wait to follow the player again
                if (hitCorner()) {
                    timeMoving = 0;
                    lock = 1;
                    lastMoved = elapsedTime ;
                }
            }
        }
    }
    private boolean hitCorner(){
        if (positionX < sizeX / 2) return true;
        if (positionY < sizeY / 2) return true;
        if (positionX > Gdx.graphics.getWidth() - sizeX / 2) return true;
        if (positionY > Gdx.graphics.getHeight() - sizeY / 2) return true;
        return false;
    }

    // this had to be changed, because the bull consider its position accorind to the center of the sprite
    // @Override
    // protected void checkBounds(){ //checks if the enemy still on bounds
    //     if (positionX < sizeX / 2) positionX = sizeX / 2;
    //     if (positionY < sizeY / 2) positionY = sizeY / 2;
    //     if (positionX > Gdx.graphics.getWidth() - sizeX / 2) positionX = Gdx.graphics.getWidth() - (sizeX / 2);
    //     if (positionY > Gdx.graphics.getHeight() - sizeY / 2) positionY = Gdx.graphics.getHeight() - (sizeY / 2);
    // }
}

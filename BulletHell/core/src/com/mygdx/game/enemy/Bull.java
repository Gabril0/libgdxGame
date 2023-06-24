package com.mygdx.game.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.player.Player;

public class Bull extends Enemy {
    // needs another render to take off the bullets

    // locks the player position so the bull can run after him
    // needs to be global, so it does not update with the render method unless needed
    private float lockedPlayerCenterX = 0;
    private float lockedPlayerCenterY = 0;
    private float attackTimer = 0;
    private Player player;

    public Bull(float positionX, float positionY, float speedX, float speedY, float health, String bulletImg, 
    String bulletType, String sprite, Player player) {
        super(positionX, positionY, speedX, speedY, health, bulletImg, bulletType, sprite);
        float width = Gdx.graphics.getWidth();

        this.positionX = positionX;
        this.positionY = positionY;
        // sets the size of the sprite on screen
        sizeX = width/15;
        sizeY = width/15;
        damage = 300;

        this.player = player;
    }

    @Override
    protected void move(float moveDuration, float stationaryTime) {
        float deltaTime = Gdx.graphics.getDeltaTime();
        elapsedTime += deltaTime;
        attackTimer += deltaTime;
        if (startupAnimation) {
            batch.setColor(Color.RED);
            intro.render(positionX - sizeX, positionY - sizeY, sizeX * 3, sizeY * 3,
                    0, batch);

            if (intro.getWasFinished()) {
                batch.setColor(Color.WHITE);
                startupAnimation = false;
            }
        } else {
            if (!isAlive) {
                batch.setColor(Color.WHITE);

                if (explosionLock) {
                    explosion.render(positionX - sizeX, positionY - sizeY, sizeX * 3, sizeY * 3,
                            0, batch);

                    if (explosion.getWasFinished()) {
                        explosionLock = false;
                    }
                }
            }

            if (isAlive) {
                if (attackTimer >= stationaryTime) {
                    if (lock == 1) {
                        // checks the direction where the player is headed
                        lockedPlayerCenterX = playerCenterX - positionX;
                        lockedPlayerCenterY = playerCenterY - positionY;
                        // calculates the straight line that leads to the player
                        float lenght = (float) Math.sqrt(Math.pow(lockedPlayerCenterX, 2) + Math.pow(lockedPlayerCenterY, 2));
                        if (lenght != 0) {
                            // normalize the result, so its always btw 0 and 1
                            lockedPlayerCenterX /= lenght;
                            lockedPlayerCenterY /= lenght;
                        }
                        // reset the lock to 0, so it can start following the player
                        lock = 0;
                    } else {
                        // follows the player
                        positionX += speedX * deltaTime * lockedPlayerCenterX;
                        positionY += speedY * deltaTime * lockedPlayerCenterY;
                        // wait to follow the player again
                        if (hitCorner()) {
                            attackTimer = 0;
                            lock = 1;
                            lastMoved = elapsedTime;
                        }
                        ;
                    }
                }
            }
        }
    }
    private boolean hitCorner(){
        if (positionX < 0) return true;
        if (positionY < 0) return true;
        if (positionX > Gdx.graphics.getWidth() - sizeX) return true;
        if (positionY > Gdx.graphics.getHeight() - sizeY) return true;
        return false;
    }

    // Renders the enemy following the pattern of the other ones
    @Override
    public void render(float playerCenterX, float playerCenterY) {
        super.render(player.getSpritePositionX(), player.getSpritePositionY());
    }

    @Override
    protected void applyMovement() {
        move(3, 2);
    }

    // This enemy doesnt shoot, so this method does nothing
    @Override
    protected void shot() {
        return;
    }
}

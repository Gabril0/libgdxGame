package com.mygdx.game.bosses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class BullBoss extends BossFundamentals{
    private float chaseSpeed = 250;
    private float elapsedTime;
    private float lastRest = 3;
    private float chaseTimer = 5;
    private float timeMoving = 0;
    private float deltaTime;
    public BullBoss(float positionX, float positionY, float speedX, float speedY, float health, String bulletImg, String bulletType, String sprite) {
        super(positionX, positionY, speedX, speedY, health, bulletImg, bulletType, sprite);
        damage = 200;
    }

    @Override
    public void render(float playerCenterX, float playerCenterY){
        deltaTime = Gdx.graphics.getDeltaTime();
        elapsedTime += deltaTime;
        batch.begin();
        if(startupAnimation){
            batch.setColor(Color.RED);
            intro.render(positionX - sizeX, positionY - sizeY, sizeX * 3, sizeY * 3,
                    0, batch);

            if (intro.getWasFinished()) {
                batch.setColor(Color.WHITE);
                startupAnimation = false;
            }
        }
        else {
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

                this.playerCenterX = playerCenterX;
                this.playerCenterY = playerCenterY;
                moveToPlayer(playerCenterX, playerCenterY);
                checkBounds();
                checkHealth();

                batch.draw(texture, positionX, positionY, sizeX / 2, sizeY / 2, sizeX,
                        sizeY, 1f, 1f, rotateToPlayer(this.playerCenterX, this.playerCenterY), 0, 0, texture.getWidth(),
                        texture.getHeight(), false, false);
                if (isHit) {
                    gotHitAnimation(deltaTime);
                }


                // Update the collider's position and rotation
                //drawCollider(getCollider());
                healthBar.renderHealthBar(this);

            }
        }
        batch.end();

    }

    public void moveToPlayer(float playerCenterX, float playerCenterY){
        if(elapsedTime > lastRest + 1) {
            if (positionX + (sizeX / 2) < playerCenterX) positionX += chaseSpeed * deltaTime;
            if (positionY + (sizeY / 2) < playerCenterY) positionY += chaseSpeed * deltaTime;
            if (positionX + (sizeX / 2) > playerCenterX) positionX -= chaseSpeed * deltaTime;
            if (positionY + (sizeY / 2) > playerCenterY) positionY -= chaseSpeed * deltaTime;
            timeMoving += deltaTime;
            if(timeMoving >= chaseTimer){
                lastRest = elapsedTime;
                timeMoving = 0;
            }
        }
    }
}

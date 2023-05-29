package com.mygdx.game.bosses;

import com.badlogic.gdx.Gdx;


import com.mygdx.game.enemy.Enemy;
import com.mygdx.game.uirelated.HealthBar;


public class BossFundamentals extends Enemy {

    public BossFundamentals(float positionX, float positionY, float speedX, float speedY, float health,
            String bulletImg, String bulletType, String sprite) {
        super(positionX, positionY, speedX, speedY, health, bulletImg, bulletType, sprite);
        sizeX = 256;
        sizeY = 256;

    }

    @Override
    public void render(float playerCenterX, float playerCenterY){
        float deltaTime = Gdx.graphics.getDeltaTime();
        if(isAlive){

            this.playerCenterX = playerCenterX;
            this.playerCenterY = playerCenterY;
            move();
            checkBounds();
            checkHealth();
            

            bulletPool.renderBulletPoolEnemy(positionX, positionY,
                    sizeX, sizeY, rotateToPlayer(this.playerCenterX, this.playerCenterY) - 90);

            batch.begin();
            batch.draw(texture, positionX, positionY, sizeX / 2, sizeY / 2, sizeX,
                    sizeY, 1f, 1f, rotateToPlayer(this.playerCenterX, this.playerCenterY), 0, 0, texture.getWidth(),
                    texture.getHeight(), false, false);
            if(isHit){gotHitAnimation(deltaTime);}
            batch.end();

            // Update the collider's position and rotation
            //drawCollider(getCollider());
            healthBar.renderHealthBar(this);

        }

    }

    public void move() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        elapsedTime += deltaTime;
        if (elapsedTime >= lastMoved + moveDuration) {
            if(lock == 1){
                randomX = moveRandomPosition();
                randomY = moveRandomPosition();
                lock = 0;
                startingMovingTime = elapsedTime;
            }
            if (positionX == 0 || positionY == 0){ 
                positionX += (deltaTime * speedX) * 1;
                positionY += (deltaTime * speedY) * 1;
            }

            if (positionX == Gdx.graphics.getWidth() - sizeX || positionY == Gdx.graphics.getHeight() - sizeY) {
                positionX += (deltaTime * speedX) * -1;
                positionY += (deltaTime * speedY) * -1;}
            else{
                positionX += (deltaTime * speedX) * randomX;
                positionY += (deltaTime * speedY) * randomY;
                timeMoving = elapsedTime - startingMovingTime ;}
            
            if(timeMoving > stationaryTime){ //condition to stop enemy stuck
                timeMoving = 0;
                lock = 1;
                lastMoved = elapsedTime ;
            }
        }
            
    }

    private float moveRandomPosition(){
        return (-1 + random.nextInt(3));
    }
}

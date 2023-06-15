package com.mygdx.game.bosses;

import com.badlogic.gdx.Gdx;


import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.enemy.Enemy;
import com.mygdx.game.listeners.EventManager;


public class BossFundamentals extends Enemy {

    public BossFundamentals(float positionX, float positionY, float speedX, float speedY, float health,
            String bulletImg, String bulletType, String sprite) {
        super(positionX, positionY, speedX, speedY, health, bulletImg, bulletType, sprite);
        float width = Gdx.graphics.getWidth();
		//float height = Gdx.graphics.getHeight();

        //atributes
        sizeX = width/6;
        sizeY = width/6;


    }

    @Override
    public void render(float playerCenterX, float playerCenterY){
        float deltaTime = Gdx.graphics.getDeltaTime();
        batch.begin();
        if(!isAlive){
            batch.setColor(Color.WHITE);

            if(explosionLock){
                explosion.render(positionX - sizeX/2, positionY - sizeY/2, sizeX * 3, sizeY * 3,
                        0, batch);

                if(explosion.getWasFinished()){
                    explosionLock = false;
                }
            }
        }
        if(isAlive){

            this.playerCenterX = playerCenterX;
            this.playerCenterY = playerCenterY;
            move();
            checkBounds();
            checkHealth();
            

            bulletPool.renderBulletPoolEnemy(positionX, positionY,
                    sizeX, sizeY, rotateToPlayer(this.playerCenterX, this.playerCenterY) - 90, damage);

            batch.draw(texture, positionX, positionY, sizeX / 2, sizeY / 2, sizeX,
                    sizeY, 1f, 1f, rotateToPlayer(this.playerCenterX, this.playerCenterY), 0, 0, texture.getWidth(),
                    texture.getHeight(), false, false);
            if(isHit){gotHitAnimation(deltaTime);}


            // Update the collider's position and rotation
            //drawCollider(getCollider());
            healthBar.renderHealthBar(this);

        }
        batch.end();

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
    public boolean died(){
        return !isAlive;
    }

    private float moveRandomPosition(){
        return (-1 + random.nextInt(3));
    }
}

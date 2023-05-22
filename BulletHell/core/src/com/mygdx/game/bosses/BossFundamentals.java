package com.mygdx.game.bosses;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.mygdx.game.bullets.BulletPool;
import com.mygdx.game.enemy.Enemy;
import com.mygdx.game.player.Player;

import java.util.Random;

public class BossFundamentals extends Enemy {
    //Rendering
    private Texture texture;
    private SpriteBatch batch;
    private ShapeRenderer collider;

    //Location
    private int sizeX = 256, sizeY = 256;
    private float positionX = 500, positionY = 500;
    private float speedX = 200, speedY = 200;
    private float randomX, randomY;
    private float stationaryTime = 1;

    private float lastMoved = 0;
    private float moveDuration = 1;
    private float elapsedTime;

    private int lock = 1;
    private float timeMoving = 0;
    private float startingMovingTime = 0;
    private float playerCenterX, playerCenterY;

    //atributtes
    private float health = 10000;

    //bullets
    BulletPool bulletPool = new BulletPool(50);

    //booleans
    private boolean isAlive = true;

    private Random random = new Random();

    public BossFundamentals(float positionX, float positionY, float speedX, float speedY, float health,
            String bulletImg, String bulletType, String sprite) {
        super(positionX, positionY, speedX, speedY, health, bulletImg, bulletType, sprite);
        
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

package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.utils.TimeUtils;

public class BulletPool {
    private float CoolDown = 0.2f; //0.2 seconds of cooldown
    private float lastTimeShot = 0;
    private int poolSize;
    private Bullet[] pool; // pool of bullets already prealocated in memory
    private float elapsedTime = 0;

    BulletPool(int poolSize) {
        this.poolSize = poolSize;
    }

    public void createBulletPool(String imgPath) { //usar bullet type depois TODO!!!!
        pool = new Bullet[poolSize];
        for (int i = 0; i < poolSize; i++) { 
            pool[i] = new SimpleBullet(-1, -1, 32, 32);
            pool[i].createBullet(imgPath);
        }

    }

    public void renderBulletPool(float playerPositionX, float playerPositionY,float playerSizeX, float playersizeY, float playerRotation){
        elapsedTime += Gdx.graphics.getDeltaTime();
        boolean isShooting = Gdx.input.isButtonPressed(Buttons.LEFT);
        for (int i = 0; i < poolSize; i++) {
            if (pool[i].getIsActive()) {
                pool[i].renderBullet(playerPositionX, playerPositionY,  playerSizeX, playersizeY, playerRotation);
                pool[i].move();
            }
        }

        if(isShooting && elapsedTime >= lastTimeShot + CoolDown){

            for (int i = 0; i < poolSize; i++) {
                //System.out.println(i);
                if(!pool[i].getIsActive()){
                    pool[i].renderBullet(playerPositionX, playerPositionY, playerSizeX, playersizeY, playerRotation);
                    pool[i].activate();
                    System.out.println("shooted");
                    lastTimeShot = elapsedTime;
                    break;
                }

                if(i == poolSize - 1 && pool[i].getIsActive() == true){
                    System.out.println("Pool is full!");
                    break;
                }  
            }
        }
    }

    public void disposeBulletPool() {
        for (int i = 0; i < poolSize; i++) {
            pool[i].disposeBullet();
        }
    }
}

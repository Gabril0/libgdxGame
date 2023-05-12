package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class BulletPool {
    private int poolSize;
    private Bullet[] pool; // pool of bullets already prealocated in memory

    BulletPool(int poolSize) {
        this.poolSize = poolSize;
    }

    public void createBulletPool(Bullet bulletType, String imgPath) {
        pool = new Bullet[poolSize];
        for (int i = 0; i < poolSize; i++) {
            pool[i] = bulletType;
            pool[i].createBullet(imgPath);
        }

    }

    public void renderBulletPool(float playerPositionX, float playerPositionY){
        if(Gdx.input.isKeyPressed(Input.Keys.G)){
            //check if there are active slots
            //activate the bullet if there is one
            for (int i = 0; i < poolSize; i++) {
                if(pool[i].getIsActive() == false){
                    pool[i].renderBullet(playerPositionX, playerPositionY);
                    pool[i].activate();
                    return;
                }
                if(i == poolSize - 1 && pool[i].getIsActive() == true){
                    System.out.println("Pool is full!");
                    return;
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

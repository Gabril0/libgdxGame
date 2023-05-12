package com.mygdx.game;

import java.util.ArrayList;

public class BulletPool {
    private Bullet bullet;
    private ArrayList <Bullet> pool  = new ArrayList<Bullet>();
    BulletPool(Bullet bullet){
        this.bullet = bullet;
    }

    public void createBulletPool(){
        bullet.createBullet();
    }
    public void renderBulletPool(){
        for (Bullet bullet : pool) {
            bullet.activate();
            bullet.renderBullet();
        }
    }
    public void disposeBulletPool(){
        bullet.disposeBullet();
    }
}

package com.mygdx.game.bullets;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.mygdx.game.bosses.BossFundamentals;
import com.mygdx.game.listeners.EventManager;
import com.mygdx.game.player.Player;


public class BulletPool {
    private float coolDown = 0.2f; //0.2 seconds of cooldown
    private float lastTimeShot = 0;
    private int poolSize;
    private Bullet[] pool; // pool of bullets already prealocated in memory
    private float elapsedTime = 0;

    private boolean isShooting = false;

    public BulletPool(int poolSize) {
        this.poolSize = poolSize;
    }

    public void createBulletPool(String imgPath, String bulletType) {

        pool = new Bullet[poolSize];
        if(bulletType.compareTo("EnemyBullet") == 0) {
            for (int i = 0; i < poolSize; i++) {
                pool[i] = new EnemyBullet(-1, -1, 32, 32);
                pool[i].createBullet(imgPath);
            }
        }
        if(bulletType.compareTo("SimpleBullet") == 0) {
            for (int i = 0; i < poolSize; i++) {
                pool[i] = new SimpleBullet(-1, -1, 32, 32);
                pool[i].createBullet(imgPath);
            }
        }

    }

    public void renderBulletPoolPlayer(float playerPositionX, float playerPositionY,float playerSizeX, float playersizeY, float playerRotation, EventManager em){

            elapsedTime += Gdx.graphics.getDeltaTime();
            isShooting = Gdx.input.isButtonPressed(Buttons.LEFT);
            em.eventCall(isShooting);
            for (int i = 0; i < poolSize; i++) {
                if (pool[i].getIsActive()) {
                    pool[i].renderBullet(playerPositionX, playerPositionY, playerSizeX, playersizeY, playerRotation);
                    pool[i].move();
                }
            }

            if (isShooting && elapsedTime >= lastTimeShot + coolDown) {

                for (int i = 0; i < poolSize; i++) {
                    //System.out.println(i);
                    if (!pool[i].getIsActive()) {
                        pool[i].renderBullet(playerPositionX, playerPositionY, playerSizeX, playersizeY, playerRotation);
                        pool[i].activate();
                        lastTimeShot = elapsedTime;
                        break;
                    }

                    if (i == poolSize - 1 && pool[i].getIsActive() == true) {
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

    

    public void checkBossCollision(BossFundamentals boss) {
        if(!boss.getDestroyed())
        for (int i = 0; i < poolSize; i++) {
            if (pool[i].getIsActive() && Intersector.overlapConvexPolygons(pool[i].getCollider(), boss.getCollider())) {
                boss.setHealth(100);
                pool[i].deactivate();
            }
        }
    }

    public void checkPlayerCollision(Player player) {
        if(player.isAlive()) {
            for (int i = 0; i < poolSize; i++) {
                if (pool[i].getIsActive() && Intersector.overlapConvexPolygons(pool[i].getCollider(), player.getCollider())) {
                    player.setHealth(100);
                    pool[i].deactivate();
                }
            }
        }
    }

    //Another implementation but without the EventManager class
    public void renderBulletPoolEnemy(float playerPositionX, float playerPositionY,float playerSizeX, float playersizeY, float playerRotation){
        elapsedTime += Gdx.graphics.getDeltaTime();
        for (int i = 0; i < poolSize; i++) {
            if (pool[i].getIsActive()) {
                pool[i].renderBullet(playerPositionX, playerPositionY,  playerSizeX, playersizeY, playerRotation);
                pool[i].move();
            }
        }

        if(elapsedTime >= lastTimeShot + coolDown){

            for (int i = 0; i < poolSize; i++) {
                //System.out.println(i);
                if(!pool[i].getIsActive()){
                    pool[i].renderBullet(playerPositionX, playerPositionY, playerSizeX, playersizeY, playerRotation);
                    pool[i].activate();
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
}

package com.mygdx.game.enemy;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;

public class Factory extends Enemy {
    private Enemy spawn;
    private Enemy[] pool;
    private float coolDown = 10;
    private float lastTimeSpawn = 0;

    public Factory(float positionX, float positionY, float speedX, float speedY, float health, String bulletImg, 
    String bulletType, String sprite){
        super(positionX, positionY, speedX, speedY, health, bulletImg, bulletType, sprite);
        float width = Gdx.graphics.getWidth();
        sizeX = width / 12;
        sizeY = width / 12;

        pool = new Enemy[20];
        populatePool();
    }

    private void populatePool(){
        for (int i = 0; i < 20; i++){
            pool[i] = new Enemy(positionX + 50, positionY, 300, 300, 1000 , 
            "EnemyBullet.png", "EnemyBullet", "star.png");
            pool[i].setAlive(false);
        }
    }

    public void addEnemiesCollision(ArrayList<Enemy> array){
        for (int i = 0; i < 20; i++) {
            array.add(pool[i]);
        }
    }


    @Override
    protected void shot() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        elapsedTime += deltaTime;

        for (int i = 0; i < 20; i++) {
            if (elapsedTime >= coolDown + lastTimeSpawn && !pool[i].isAlive){
                pool[i].setAlive(true);
                lastTimeSpawn = elapsedTime;
            }
        }
    }

    @Override
    protected void renderVariations() {
        batch.draw(texture, positionX, positionY, sizeX / 2, sizeY / 2, sizeX,
            sizeY, 1f, 1f, 0, 0, 0, texture.getWidth(),
            texture.getHeight(), false, false);
        for (Enemy e : pool){
            e.render(playerCenterX, playerCenterY);
        }
    }
}

package com.mygdx.game.enemy;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.animation.Animation;
import com.mygdx.game.animation.FactoryAnimation;
import com.mygdx.game.constants.EnemyConstants;
import com.mygdx.game.constants.SpriteConstants;

public class Factory extends Enemy {
    private String enemyType;
    private Enemy[] pool;
    private float coolDown = 10;
    private float lastTimeSpawn = 0;
    private Animation animation;

    public Factory(float positionX, float positionY, float speedX, float speedY, float health, String bulletImg, 
    String bulletType, String sprite, String enemyType){
        super(positionX, positionY, speedX, speedY, health, bulletImg, bulletType, sprite);

        animation = new FactoryAnimation();
        animation.create();

        float width = Gdx.graphics.getWidth();
        sizeX = width / 12;
        sizeY = width / 12;

        this.enemyType = enemyType;

        pool = new Enemy[20];
        populatePool();
    }

    // Factory for all types of enemies
    private void populatePool(){
        if (enemyType.compareTo("Enemy") == 0)
            for (int i = 0; i < 20; i++){
                pool[i] = new Enemy(positionX + 50, positionY, EnemyConstants.ENEMY_SPEED, EnemyConstants.ENEMY_SPEED, EnemyConstants.HEALTH, 
                SpriteConstants.STAR_BULLET, "EnemyBullet", SpriteConstants.ENEMY);
                pool[i].setAlive(false);
            }
        // if (enemyType.compareTo("Bull") == 0)
        //     for (int i = 0; i < 20; i++){
        //         pool[i] = new Bull(positionX + 50, positionY, 700, 700, 1000, 
        //         "EnemyBullet.png", "EnemyBullet", "bull.png");
        //         pool[i].setAlive(false);
        //     }
        // The giant face spawns randomly at the screen, so doesnt give the effect of spawning from the factory
        // if (enemyType.compareTo("GiantFace") == 0)
        //     for (int i = 0; i < 20; i++){
        //         pool[i] = new GiantFace(positionX + 50, positionY, 100, 100, 1000,
        //         "EnemyBullet.png", "EnemyBullet", "giantFace.png");
        //         pool[i].setAlive(false);
        //     }
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
            if (elapsedTime >= coolDown + lastTimeSpawn && !pool[i].isAlive()){
                pool[i].setAlive(true);
                lastTimeSpawn = elapsedTime;
            }
            if (!pool[i].isAlive()){
                pool[i].setAlive(false);
            }
        }
    }

    @Override
    protected void renderVariations() {
        animation.render(positionX, positionY, sizeX, sizeY, 0, batch);

        // batch.draw(texture, positionX, positionY, sizeX / 2, sizeY / 2, sizeX,
        //     sizeY, 1f, 1f, 0, 0, 0, texture.getWidth(),
        //     texture.getHeight(), false, false);
        // The Bull needs a specific renderer where it gets the player position, not the center
        // if (enemyType.compareTo("Bull") == 0)
        //     for (Enemy bull : pool){
        //         e.render()
        //     }
        for (Enemy e : pool){
            e.render(playerCenterX, playerCenterY);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        animation.dispose();
    }
}

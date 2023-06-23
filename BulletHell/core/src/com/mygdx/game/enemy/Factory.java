package com.mygdx.game.enemy;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.animation.Animation;
import com.mygdx.game.animation.FactoryAnimation;
import com.mygdx.game.constants.BulletConstants;
import com.mygdx.game.constants.EnemyConstants;
import com.mygdx.game.constants.SpriteConstants;
import com.mygdx.game.player.Player;

public class Factory extends Enemy {
    private String enemyType;
    private Enemy[] pool;
    private float coolDown = 10;
    private float lastTimeSpawn = 0;
    private Animation animation;
    private Player player;

    public Factory(float positionX, float positionY, float speedX, float speedY, float health, String bulletImg, 
    String bulletType, String sprite, String enemyType, Player player){
        super(positionX, positionY, speedX, speedY, health, bulletImg, bulletType, sprite);

        animation = new FactoryAnimation();
        animation.create();

        float width = Gdx.graphics.getWidth();
        sizeX = width / 12;
        sizeY = width / 12;

        this.enemyType = enemyType;

        pool = new Enemy[20];
        this.player = player;
        populatePool();
    }

    // Factory for all types of enemies
    // Creates a pool with the enemy specified at the constructor
    private void populatePool(){
        if (enemyType.compareTo("Enemy") == 0)
            for (int i = 0; i < 20; i++){
                pool[i] = new Enemy(positionX + 50, positionY, EnemyConstants.ENEMY_SPEED, EnemyConstants.ENEMY_SPEED, EnemyConstants.HEALTH, 
                SpriteConstants.STAR_BULLET, BulletConstants.ENEMY, SpriteConstants.ENEMY);
                pool[i].setAlive(false);
            }
        if (enemyType.compareTo("Bull") == 0)
            for (int i = 0; i < 20; i++){
                pool[i] = new Bull(positionX + 50, positionY, EnemyConstants.BULL_SPEED, EnemyConstants.BULL_SPEED, EnemyConstants.HEALTH, 
                SpriteConstants.ENEMY_BULLET, BulletConstants.ENEMY, SpriteConstants.BULL, player);
                pool[i].setAlive(false);
            }
        if (enemyType.compareTo("GiantFace") == 0)
            for (int i = 0; i < 20; i++){
                pool[i] = new GiantFace(positionX + 50, positionY, EnemyConstants.SPEED, EnemyConstants.SPEED, EnemyConstants.HEALTH,
                SpriteConstants.ENERGY_BULLET, BulletConstants.ENEMY, SpriteConstants.GIANT_FACE);
                pool[i].setAlive(false);
            }
        if (enemyType.compareTo("Fire") == 0)
            for (int i = 0; i < 20; i++){
                pool[i] = new GiantFace(positionX + 50, positionY, EnemyConstants.SPEED, EnemyConstants.SPEED, EnemyConstants.HEALTH,
                SpriteConstants.ENEMY_BULLET, BulletConstants.ENEMY, SpriteConstants.GIANT_FACE);
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
        for (Enemy e : pool){
            e.render(playerCenterX, playerCenterY);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        animation.dispose();
        for (Enemy e : pool)
            e.dispose();
    }
}

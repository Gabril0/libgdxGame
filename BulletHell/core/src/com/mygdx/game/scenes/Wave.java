package com.mygdx.game.scenes;

import com.mygdx.game.enemy.Enemy;

import java.util.ArrayList;

public class Wave {
    protected boolean isActive = true;
    protected ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public void createWave(ArrayList<Enemy> enemies){
        this.enemies = enemies;
    }
    public void run(float playerCenterX, float playerCenterY){
        for (Enemy enemy : enemies) {
            enemy.render(playerCenterX,playerCenterY);
        }
    }
    public boolean allEnemiesDead() {
        for (Enemy enemy : enemies) {
            if (enemy.isAlive()) {
                return false;
            }
        }
        return true;
    }
    public boolean IsActive(){
        isActive = allEnemiesDead();
        return isActive;
    }
    public void setIsActive(boolean isActive){
        this.isActive = isActive;
    }

    public void dispose(){
        for (Enemy enemy : enemies) {
            enemy.dispose();
        }
    }
}

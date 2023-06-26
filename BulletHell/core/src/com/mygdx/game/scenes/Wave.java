package com.mygdx.game.scenes;

import com.mygdx.game.enemy.Enemy;
import com.mygdx.game.player.Player;

import java.util.ArrayList;

public class Wave {
    private boolean isActive = false;
    protected Player player;
    protected boolean wasBeaten = false; //used only for boss and evolution logic
    private boolean turn = false;
    protected ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public void createWave(ArrayList<Enemy> enemies, Player player){
        this.enemies = enemies;
        this.player = player;
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
        wasBeaten = true;
        return true;
    }
    public boolean isActive(){
        isActive = !allEnemiesDead();
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

    public void collisionTest() {
        for (Enemy enemy : enemies) {
//            if (shieldEnemy.isAlive()){
//                player.checkCollision(shieldEnemy);
//            }
            //else {
                player.checkCollision(enemy);
            //}
            enemy.checkCollision(player);
        }
    }

    public boolean getTurn() {
        return turn;
    }
    public void setTurn(boolean turn){
        this.turn = turn;
    }

    public void setWasBeaten(boolean wasBeaten) {
        this.wasBeaten = wasBeaten;
    }

    public boolean wasBeaten() {
        return wasBeaten;
    }
}

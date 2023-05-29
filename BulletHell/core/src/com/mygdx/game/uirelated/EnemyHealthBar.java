package com.mygdx.game.uirelated;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.enemy.Enemy;

public class EnemyHealthBar extends HealthBar{
    private int lock = 1;
    @Override
    public void createHealthBar(){
        lifeBar = new Texture("EnemyHealthBar.png");
        lifeBarBg = new Texture("HealthBarBg.png");
        batch = new SpriteBatch();
        currentSizeX = 128;
        bgSizeX = currentSizeX;
    }

    public void renderHealthBar(Enemy enemy){
        currentTime += Gdx.graphics.getDeltaTime();
        if(enemy.isAlive()){
            batch.begin();
            currentSizeX = ((enemy.getHealth())/50);
            if(lock == 1){
                bgSizeX = currentSizeX;
                lock = 0;
            }
            float offsetY = ((enemy.getSizeY()/2) - enemy.getSizeY()/4);
            float offsetX = ((enemy.getSizeX()/2) - bgSizeX/2);
            if( enemy.getPositionY() < Gdx.graphics.getWidth()/20) offsetY = -((enemy.getSizeY()) + 16); //checks if enemy is close to screen's lower edge
            float xPos = enemy.getPositionX() + offsetX;
            float yPos = enemy.getPositionY() - offsetY;
            batch.draw(lifeBarBg, xPos, yPos, bgSizeX ,  sizeY);
            batch.draw(lifeBar, xPos, yPos, currentSizeX ,  sizeY);

            batch.end();
        }
    }
}

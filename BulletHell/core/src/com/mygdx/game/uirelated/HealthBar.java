package com.mygdx.game.uirelated;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.player.Player;

public class HealthBar {
    private Texture lifeBar;
    private SpriteBatch batch;

    private float currentSizeX = 64, sizeY = 8;
    private float playerPositionX, playerPositionY;

    private float currentTime = 0, animationTime = 1, timeHit;

    private int lock = 1;


    public void createHealthBar(){
        lifeBar = new Texture("HealthBar.png");
        batch = new SpriteBatch();
        currentSizeX = 64;
    }

    public void renderHealthBar(Player player){ //do listener later instead of instantiating the player again
        currentTime += Gdx.graphics.getDeltaTime();
        if(player.gotHit() && player.isAlive()){
            batch.begin();
            currentSizeX = - player.getHealth()/10;
            if(lock == 1){
                timeHit = currentTime;
                lock = 0;}
            if(currentTime < timeHit + animationTime) {
                batch.draw(lifeBar, player.getSpritePositionX() + (player.getSpriteSizeX()/2), player.getSpritePositionY() - ((player.getSpriteSizeY()/2) - 16), currentSizeX ,  sizeY);
            }
            else{
                lock = 1;
                player.setGotHit();
            }
            batch.end();
        }
    }

    public void disposeHealthBar(){
        lifeBar.dispose();
        batch.dispose();
    }

    public void heal(int healAmount){
        currentSizeX = currentSizeX + healAmount;
    }
}

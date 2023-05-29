package com.mygdx.game.uirelated;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.player.Player;

public class HealthBar {
    protected Texture lifeBar;
    protected Texture lifeBarBg;
    protected SpriteBatch batch;

    protected float bgSizeX; //the Y is the same as the life bar
    protected float currentSizeX = 64, sizeY = 8;

    protected float currentTime = 0, animationTime = 1, timeHit;

    protected int lock = 1;


    public void createHealthBar(){
        lifeBar = new Texture("HealthBar.png");
        lifeBarBg = new Texture("HealthBarBg.png");
        batch = new SpriteBatch();
        currentSizeX = 64;
        bgSizeX = currentSizeX;
    }

    public void renderHealthBar(Player player){
        currentTime += Gdx.graphics.getDeltaTime();
        if(player.gotHit() && player.isAlive()){
            batch.begin();
            currentSizeX = ((player.getHealth())/10);
            if(lock == 1){
                timeHit = currentTime;
                lock = 0;}
            if(currentTime < timeHit + animationTime) {
                float offsetY = ((player.getSpriteSizeY()/2) - player.getSpriteSizeY()/4);
                float offsetX = ((player.getSpriteSizeX()/2) - bgSizeX/2);
                if( player.getSpritePositionY() < Gdx.graphics.getWidth()/20) offsetY = -((player.getSpriteSizeY()) + 16); //checks if player is close to screen's lower edge
                float xPos = player.getSpritePositionX() + offsetX;
                float yPos = player.getSpritePositionY() - offsetY;
                batch.draw(lifeBarBg, xPos, yPos, bgSizeX ,  sizeY);
                batch.draw(lifeBar, xPos, yPos, currentSizeX ,  sizeY);
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

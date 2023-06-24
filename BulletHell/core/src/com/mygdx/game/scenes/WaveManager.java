package com.mygdx.game.scenes;

import com.mygdx.game.enemy.Enemy;
import com.mygdx.game.player.Player;

import java.util.ArrayList;

public class WaveManager {
    protected ArrayList<Wave> waves = new ArrayList<Wave>();

    public void createWaves(float width, float height, Player player){
        //need implementation in each class
    }
    public void renderWaves(float playerCenterX, float playerCenterY){
        for(Wave wave : waves){
            if(wave.isActive()){
                wave.run(playerCenterX, playerCenterY);
            }
        }
    }

    public void collisionTest(){
        for(Wave wave : waves){
            if(wave.getTurn()){
                wave.collisionTest();
            }
        }
    }

    public void disposeWaves(){
        for(Wave wave : waves){
            wave.dispose();
        }
    }
}

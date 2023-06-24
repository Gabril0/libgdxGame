package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.constants.SpriteConstants;
import com.mygdx.game.enemy.*;
import com.mygdx.game.player.Player;

import java.util.ArrayList;

public class WavesStage1 extends WaveManager{
    private Wave []wave = new Wave[15];
    private ArrayList<Enemy> enemiesToAdd[] = new ArrayList[15];
    private boolean timeLock = true; //timelock for waiting between waves
    private float deltaTime, elapsedTime = 0, finishTime;
    private int totalWaves;
    @Override
    public void createWaves(float width, float height, Player player) {
        for(int i = 0; i < 15 ; i++){
            wave[i] = new Wave();
        }
        for(int i = 0; i< 15 ; i++){
            enemiesToAdd[i] = new ArrayList<Enemy>();
        }

        //creation of a wave

            enemiesToAdd[1].add(
                    new Bull(0, height - 1, 700, 700, 1000,
                            SpriteConstants.ENEMY_BULLET, "EnemyBullet", SpriteConstants.BULL, player)
            );
            enemiesToAdd[1].add(
                    new GiantFace(0, height / 2, 100, 100, 1000,
                            SpriteConstants.ENERGY_BULLET, "EnemyBullet", SpriteConstants.GIANT_FACE)
            );
            wave[1].createWave(enemiesToAdd[1], player);
            waves.add(wave[1]);

        //End of the process

            enemiesToAdd[2].add(
                    new Fire(1000, height / 2, 0, 0, 1000,
                            SpriteConstants.ENEMY_BULLET, "EnemyBullet", SpriteConstants.FIRE)
            );
            enemiesToAdd[2].add(
                    new Mirror(0, height / 2, 100, 100, 1000,
                            "Bullets/EnemyBullet.png", "EnemyBullet", SpriteConstants.MIRROR)
            );
            wave[2].createWave(enemiesToAdd[2], player);
            waves.add(wave[2]);

            totalWaves = 2;

            //enemiesToAdd.clear();

    }

    @Override
    public void renderWaves(float playerCenterX, float playerCenterY){
        deltaTime = Gdx.graphics.getDeltaTime();
        elapsedTime += deltaTime;

        if(wave[1].isActive()){wave[1].setTurn(true);} //start condition

        for(int i = 1; i <= totalWaves; i++) {
            waveRunner(i, playerCenterX, playerCenterY);
        }

//        for(Wave wave : waves){
//            if(wave.IsActive()){
//                wave.run(playerCenterX, playerCenterY);
//            }
//        }
    }

    public void waveRunner(int waveNumber, float playerCenterX, float playerCenterY){
        if(wave[waveNumber].getTurn()){
            wave[waveNumber].run(playerCenterX, playerCenterY);
            if(wave[waveNumber].allEnemiesDead()){
                if(timeLock){
                    finishTime = elapsedTime;
                    timeLock = false;
                }
                if(elapsedTime > finishTime + waveNumber) {
                    wave[waveNumber + 1].setTurn(true);
                    wave[waveNumber].setTurn(false);
                    timeLock = true;
                }
            }
        }
    }
}

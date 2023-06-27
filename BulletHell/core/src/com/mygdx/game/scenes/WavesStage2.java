package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.bosses.BlackHole;
import com.mygdx.game.bosses.BullBoss;
import com.mygdx.game.bosses.Satellite;
import com.mygdx.game.bosses.SpaceMan;
import com.mygdx.game.constants.EnemyConstants;
import com.mygdx.game.constants.SpriteConstants;
import com.mygdx.game.enemy.*;
import com.mygdx.game.player.Player;

import java.util.ArrayList;

public class WavesStage2 extends WaveManager{
    private Wave []wave = new Wave[17];
    private ArrayList<Enemy> enemiesToAdd[] = new ArrayList[17];
    private boolean timeLock = true, isOver = false; //timelock for waiting between waves
    private float deltaTime, elapsedTime = 0, finishTime, waveCooldown = 0.5f;
    private int totalWaves;
    @Override
    public void createWaves(float width, float height, Player player) {
        for(int i = 0; i <= 16 ; i++){
            wave[i] = new Wave();
        }
        for(int i = 0; i <= 16 ; i++){
            enemiesToAdd[i] = new ArrayList<Enemy>();
        }
        //wave 1
        //creation of a wave

        enemiesToAdd[1].add(
                new Enemy(width * 0.15f, height  * 0.15f, EnemyConstants.ENEMY_SPEED, EnemyConstants.ENEMY_SPEED, EnemyConstants.HEALTH,
                        SpriteConstants.STAR_BULLET, "EnemyBullet", SpriteConstants.ENEMY)
        );
        enemiesToAdd[1].add(
                new Enemy(width * 0.85f, height  * 0.15f, EnemyConstants.ENEMY_SPEED, EnemyConstants.ENEMY_SPEED, EnemyConstants.HEALTH,
                        SpriteConstants.STAR_BULLET, "EnemyBullet", SpriteConstants.ENEMY)
        );
        enemiesToAdd[1].add(
                new Shield(width * 0.5f, height  * 0.85f, 0, 0, 2000, "Bullets/EnemyBullet.png"
                        , "EnemyBullet", SpriteConstants.SHIELD)
        );

        wave[1].createWave(enemiesToAdd[1], player);
        waves.add(wave[1]);

        //End of the process

        //wave 2
        enemiesToAdd[2].add(
                new Fire(width * 0.5f, height  * 0.5f, 0, 0, 1000,
		SpriteConstants.ENEMY_BULLET, "EnemyBullet", SpriteConstants.FIRE)
        );
        enemiesToAdd[2].add(
                new Shield(width * 0.15f, height  * 0.15f, 0, 0, 2000, "Bullets/EnemyBullet.png"
                        , "EnemyBullet", SpriteConstants.SHIELD)
        );
        enemiesToAdd[2].add(
                new Shield(width * 0.85f, height  * 0.85f, 0, 0, 2000, "Bullets/EnemyBullet.png"
                        , "EnemyBullet", SpriteConstants.SHIELD)
        );

        wave[2].createWave(enemiesToAdd[2], player);
        waves.add(wave[2]);

        //wave 3
        enemiesToAdd[3].add(
                new Bull(width * 0.75f, height  * 0.75f, 700, 700, 1000,
		SpriteConstants.ENEMY_BULLET, "EnemyBullet", SpriteConstants.BULL, player)
        );
        enemiesToAdd[3].add(
                new Bull(width * 0.75f, height  * 0.50f, 700, 700, 1000,
                        SpriteConstants.ENEMY_BULLET, "EnemyBullet", SpriteConstants.BULL, player)
        );
        enemiesToAdd[3].add(
                new Bull(width * 0.75f, height  * 0.25f, 700, 700, 1000,
                        SpriteConstants.ENEMY_BULLET, "EnemyBullet", SpriteConstants.BULL, player)
        );
        enemiesToAdd[3].add(
                new Shield(width * 0.9f, height  * 0.9f, 0, 0, 2000, "Bullets/EnemyBullet.png"
                        , "EnemyBullet", SpriteConstants.SHIELD)
        );



        wave[3].createWave(enemiesToAdd[3], player);
        waves.add(wave[3]);

        //wave 4
        enemiesToAdd[4].add(
                new Shield(width * 0.1f, height  * 0.1f, 0, 0, 2000, "Bullets/EnemyBullet.png"
                        , "EnemyBullet", SpriteConstants.SHIELD)
        );
        enemiesToAdd[4].add(
                new GiantFace(width * 0.5f, height  * 0.85f, 100, 100, 1000,
                        SpriteConstants.ENERGY_BULLET, "EnemyBullet", SpriteConstants.GIANT_FACE)
        );
        enemiesToAdd[4].add(
                new GiantFace(width * 0.25f, height  * 0.65f, 100, 100, 1000,
                        SpriteConstants.ENERGY_BULLET, "EnemyBullet", SpriteConstants.GIANT_FACE)
        );
        enemiesToAdd[4].add(
                new GiantFace(width * 0.85f, height  * 0.65f, 100, 100, 1000,
                        SpriteConstants.ENERGY_BULLET, "EnemyBullet", SpriteConstants.GIANT_FACE)
        );
        enemiesToAdd[4].add(
                new GiantFace(width * 0.65f, height  * 0.25f, 100, 100, 1000,
                        SpriteConstants.ENERGY_BULLET, "EnemyBullet", SpriteConstants.GIANT_FACE)
        );
        enemiesToAdd[4].add(
                new GiantFace(width * 0.35f, height  * 0.25f, 100, 100, 1000,
                        SpriteConstants.ENERGY_BULLET, "EnemyBullet", SpriteConstants.GIANT_FACE)
        );


        wave[4].createWave(enemiesToAdd[4], player);
        waves.add(wave[4]);

        //wave 5
        enemiesToAdd[5].add(
                new GiantFace(width * 0.25f, height  * 0.5f, 100, 100, 1000,
                        SpriteConstants.ENERGY_BULLET, "EnemyBullet", SpriteConstants.GIANT_FACE)
        );
        enemiesToAdd[5].add(
                new Shield(width * 0.9f, height  * 0.5f, 0, 0, 2000, "Bullets/EnemyBullet.png"
                        , "EnemyBullet", SpriteConstants.SHIELD)
        );
        enemiesToAdd[5].add(
                new Fire(width * 0.85f, height * 0.5f, 0, 0, 1000,
                        SpriteConstants.ENEMY_BULLET, "EnemyBullet", SpriteConstants.FIRE)
        );
        wave[5].createWave(enemiesToAdd[5], player);
        waves.add(wave[5]);

        //wave 6
        enemiesToAdd[6].add(
                new Shield(width * 0.85f, height  * 0.5f, 0, 0, 2000, "Bullets/EnemyBullet.png"
                        , "EnemyBullet", SpriteConstants.SHIELD)
        );
        enemiesToAdd[6].add(
                new GiantFace(width * 0.8f, height  * 0.5f, 100, 100, 1000,
                        SpriteConstants.ENERGY_BULLET, "EnemyBullet", SpriteConstants.GIANT_FACE)
        );
        enemiesToAdd[6].add(
                new GiantFace(width * 0.2f, height  * 0.5f, 100, 100, 1000,
                        SpriteConstants.ENERGY_BULLET, "EnemyBullet", SpriteConstants.GIANT_FACE)
        );
        enemiesToAdd[6].add(
                new Bull(width * 0.25f, height  * 0.9f, 700, 700, 1000,
                        SpriteConstants.ENEMY_BULLET, "EnemyBullet", SpriteConstants.BULL, player)
        );
        wave[6].createWave(enemiesToAdd[6], player);
        waves.add(wave[6]);

        //wave 7
        enemiesToAdd[7].add(
                new Bull(width * 0.25f, height  * 0.25f, 700, 700, 1000,
                        SpriteConstants.ENEMY_BULLET, "EnemyBullet", SpriteConstants.BULL, player)
        );
        enemiesToAdd[7].add(
                new Bull(width * 0.25f, height  * 0.75f, 700, 700, 1000,
                        SpriteConstants.ENEMY_BULLET, "EnemyBullet", SpriteConstants.BULL, player)
        );
        enemiesToAdd[7].add(
                new Bull(width * 0.75f, height  * 0.25f, 700, 700, 1000,
                        SpriteConstants.ENEMY_BULLET, "EnemyBullet", SpriteConstants.BULL, player)
        );
        enemiesToAdd[7].add(
                new Bull(width * 0.75f, height  * 0.75f, 700, 700, 1000,
                        SpriteConstants.ENEMY_BULLET, "EnemyBullet", SpriteConstants.BULL, player)
        );
        enemiesToAdd[7].add(
                new Bull(width * 0.5f, height  * 0.9f, 700, 700, 1000,
                        SpriteConstants.ENEMY_BULLET, "EnemyBullet", SpriteConstants.BULL, player)
        );
        enemiesToAdd[7].add(
                new Bull(width * 0.75f, height  * 0.1f, 700, 700, 1000,
                        SpriteConstants.ENEMY_BULLET, "EnemyBullet", SpriteConstants.BULL, player)
        );
        enemiesToAdd[7].add(
                new Shield(width * 0.85f, height  * 0.5f, 0, 0, 2000, "Bullets/EnemyBullet.png"
                        , "EnemyBullet", SpriteConstants.SHIELD)
        );
        wave[7].createWave(enemiesToAdd[7], player);
        waves.add(wave[7]);

        //wave 8
        enemiesToAdd[8].add(
                new BullBoss(width * 0.5f, height  * 0.5f, 60, 60, 10000, SpriteConstants.ENEMY_BULLET, "EnergyBullet",
				SpriteConstants.BULL_BOSS)
        );
        wave[8].createWave(enemiesToAdd[8], player);
        waves.add(wave[8]);

        //wave 9
        enemiesToAdd[9].add(
                new Factory(width * 0.5f, height  * 0.5f, 0, 0, 3000,
		"Bullets/EnemyBullet.png", "EnemyBullet", SpriteConstants.FACTORY, "Enemy", player)
        );
        enemiesToAdd[9].add(
                new Shield(width * 0.9f, height  * 0.9f, 0, 0, 1000, "Bullets/EnemyBullet.png"
                        , "EnemyBullet", SpriteConstants.SHIELD)
        );
        wave[9].createWave(enemiesToAdd[9], player);
        waves.add(wave[9]);

        //wave 10
        enemiesToAdd[10].add(
                new Factory(width * 0.5f, height  * 0.9f, 0, 0, 3000,
                        "Bullets/EnemyBullet.png", "EnemyBullet", SpriteConstants.FACTORY, "Enemy", player)
        );
        enemiesToAdd[10].add(
                new Shield(width * 0.1f, height  * 0.1f, 0, 0, 3000, "Bullets/EnemyBullet.png"
                        , "EnemyBullet", SpriteConstants.SHIELD)
        );
        enemiesToAdd[10].add(
                new Fire(width * 0.5f, height  * 0.5f, 0, 0, 1000,
		SpriteConstants.ENEMY_BULLET, "EnemyBullet", SpriteConstants.FIRE)
        );
        wave[10].createWave(enemiesToAdd[10], player);
        waves.add(wave[10]);

        //wave 11
        enemiesToAdd[11].add(
                new Factory(width * 0.1f, height  * 0.9f, 0, 0, 3000,
                        "Bullets/EnemyBullet.png", "EnemyBullet", SpriteConstants.FACTORY, "Bull", player)
        );
        enemiesToAdd[11].add(
                new GiantFace(width * 0.15f, height  * 0.15f, 100, 100, 1000,
                        SpriteConstants.ENERGY_BULLET, "EnemyBullet", SpriteConstants.GIANT_FACE)
        );
        enemiesToAdd[11].add(
                new GiantFace(width * 0.85f, height  * 0.85f, 100, 100, 1000,
                        SpriteConstants.ENERGY_BULLET, "EnemyBullet", SpriteConstants.GIANT_FACE)
        );
        wave[11].createWave(enemiesToAdd[11], player);
        waves.add(wave[11]);

        //wave 12
        enemiesToAdd[12].add(
                new Factory(width * 0.1f, height  * 0.9f, 0, 0, 3000,
                        "Bullets/EnemyBullet.png", "EnemyBullet", SpriteConstants.FACTORY, "GiantFace", player)
        );
        enemiesToAdd[12].add(
                new Bull(width * 0.15f, height  * 0.15f, 700, 700, 1000,
                        SpriteConstants.ENEMY_BULLET, "EnemyBullet", SpriteConstants.BULL, player)
        );
        enemiesToAdd[12].add(
                new Bull(width * 0.15f, height  * 0.85f, 700, 700, 1000,
                        SpriteConstants.ENEMY_BULLET, "EnemyBullet", SpriteConstants.BULL, player)
        );
        enemiesToAdd[12].add(
                new Bull(width * 0.85f, height  * 0.15f, 700, 700, 1000,
                        SpriteConstants.ENEMY_BULLET, "EnemyBullet", SpriteConstants.BULL, player)
        );
        wave[12].createWave(enemiesToAdd[12], player);
        waves.add(wave[12]);

        //wave 13
        enemiesToAdd[13].add(
                new Factory(width * 0.5f, height  * 0.9f, 0, 0, 3000,
                        "Bullets/EnemyBullet.png", "EnemyBullet", SpriteConstants.FACTORY, "GiantFace", player)
        );
        enemiesToAdd[13].add(
                new Factory(width * 0.5f, height  * 0.9f, 0, 0, 3000,
                        "Bullets/EnemyBullet.png", "EnemyBullet", SpriteConstants.FACTORY, "GiantFace", player)
        );
        enemiesToAdd[13].add(
                new Shield(width * 0.9f, height  * 0.5f, 0, 0, 1000, "Bullets/EnemyBullet.png"
                        , "EnemyBullet", SpriteConstants.SHIELD)
        );
        enemiesToAdd[13].add(
                new Shield(width * 0.1f, height  * 0.5f, 0, 0, 1000, "Bullets/EnemyBullet.png"
                        , "EnemyBullet", SpriteConstants.SHIELD)
        );
        wave[13].createWave(enemiesToAdd[13], player);
        waves.add(wave[13]);

        //wave 14

        enemiesToAdd[14].add(
                new Enemy(width * 0.15f, height  * 0.85f, EnemyConstants.ENEMY_SPEED, EnemyConstants.ENEMY_SPEED, EnemyConstants.HEALTH,
                        SpriteConstants.STAR_BULLET, "EnemyBullet", SpriteConstants.ENEMY)
        );

        wave[14].createWave(enemiesToAdd[14], player);
        waves.add(wave[14]);

        //wave 15
        enemiesToAdd[15].add(
                new BlackHole(width * 0.5f, height  * 0.5f, 300, 300, 10000, SpriteConstants.STAR_BULLET, "EnemyBullet",
				SpriteConstants.BLACKHOLE, player)
        );
        wave[15].createWave(enemiesToAdd[15], player);
        waves.add(wave[15]);


        totalWaves = 16;

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
        if(wave[totalWaves].wasBeaten){setIsOver(true);}

//        for(Wave wave : waves){
//            if(wave.IsActive()){
//                wave.run(playerCenterX, playerCenterY);
//            }
//        }
    }

    public boolean checkEvolution(){
        if(wave[8].wasBeaten){//doing just to go to evolution screen, 8 is the boss wave
            wave[8].setWasBeaten(false);
            return  true;
        }
        return false;
    }

    public void waveRunner(int waveNumber, float playerCenterX, float playerCenterY){
        if(wave[waveNumber].getTurn()){
            wave[waveNumber].run(playerCenterX, playerCenterY);
            if(wave[waveNumber].allEnemiesDead()){
                if(timeLock){
                    finishTime = elapsedTime;
                    timeLock = false;
                }
                if(elapsedTime > finishTime + waveCooldown) {
                    wave[waveNumber + 1].setTurn(true);
                    wave[waveNumber].setTurn(false);
                    if(waveNumber<totalWaves) {
                        wave[waveNumber].dispose();
                    }
                    timeLock = true;
                }
            }
        }
    }

    public boolean isOver(){
        return isOver;
    }

    public void setIsOver(boolean bool){
        isOver = bool;
    }
    public void dispose(){
//        for(int i = 0; i <= 16 ; i++){
//            wave[i].dispose();
//        }
        for(int i = 0; i <= 16 ; i++){
            enemiesToAdd[i].clear();
        }
        disposeWaves();
    }
}


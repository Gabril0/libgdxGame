package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.constants.EnemyConstants;
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
        //wave 1
        //creation of a wave

            enemiesToAdd[1].add(
                    new Enemy(width * 0.15f, height  * 0.18f, EnemyConstants.ENEMY_SPEED, EnemyConstants.ENEMY_SPEED, EnemyConstants.HEALTH,
				SpriteConstants.STAR_BULLET, "EnemyBullet", SpriteConstants.ENEMY)
            );
            enemiesToAdd[1].add(
                new Enemy(width * 0.85f, height  * 0.15f, EnemyConstants.ENEMY_SPEED, EnemyConstants.ENEMY_SPEED, EnemyConstants.HEALTH,
                        SpriteConstants.STAR_BULLET, "EnemyBullet", SpriteConstants.ENEMY)
            );

            wave[1].createWave(enemiesToAdd[1], player);
            waves.add(wave[1]);

        //End of the process

        //wave 2
        enemiesToAdd[2].add(
                new Enemy(width * 0.50f, height  * 0.85f, EnemyConstants.ENEMY_SPEED, EnemyConstants.ENEMY_SPEED, EnemyConstants.HEALTH,
                        SpriteConstants.STAR_BULLET, "EnemyBullet", SpriteConstants.ENEMY)
        );
        enemiesToAdd[2].add(
                new Enemy(width * 0.75f, height  * 0.25f, EnemyConstants.ENEMY_SPEED, EnemyConstants.ENEMY_SPEED, EnemyConstants.HEALTH,
                        SpriteConstants.STAR_BULLET, "EnemyBullet", SpriteConstants.ENEMY)
        );
        enemiesToAdd[2].add(
                new Enemy(width * 0.25f, height  * 0.25f, EnemyConstants.ENEMY_SPEED, EnemyConstants.ENEMY_SPEED, EnemyConstants.HEALTH,
                        SpriteConstants.STAR_BULLET, "EnemyBullet", SpriteConstants.ENEMY)
        );

        wave[2].createWave(enemiesToAdd[2], player);
        waves.add(wave[2]);


        //wave 3
        enemiesToAdd[3].add(
                new Enemy(width * 0.15f, height  * 0.15f, EnemyConstants.ENEMY_SPEED, EnemyConstants.ENEMY_SPEED, EnemyConstants.HEALTH,
                        SpriteConstants.STAR_BULLET, "EnemyBullet", SpriteConstants.ENEMY)
        );
        enemiesToAdd[3].add(
                new Enemy(width * 0.85f, height  * 0.15f, EnemyConstants.ENEMY_SPEED, EnemyConstants.ENEMY_SPEED, EnemyConstants.HEALTH,
                        SpriteConstants.STAR_BULLET, "EnemyBullet", SpriteConstants.ENEMY)
        );
        enemiesToAdd[3].add(
                new Enemy(width * 0.85f, height  * 0.85f, EnemyConstants.ENEMY_SPEED, EnemyConstants.ENEMY_SPEED, EnemyConstants.HEALTH,
                        SpriteConstants.STAR_BULLET, "EnemyBullet", SpriteConstants.ENEMY)
        );
        enemiesToAdd[3].add(
                new Enemy(width * 0.15f, height  * 0.85f, EnemyConstants.ENEMY_SPEED, EnemyConstants.ENEMY_SPEED, EnemyConstants.HEALTH,
                        SpriteConstants.STAR_BULLET, "EnemyBullet", SpriteConstants.ENEMY)
        );



        wave[3].createWave(enemiesToAdd[3], player);
        waves.add(wave[3]);

        //wave 4

        enemiesToAdd[4].add(
                 new Fire(width * 0.5f, height * 0.5f, 0, 0, 1000,
		SpriteConstants.ENEMY_BULLET, "EnemyBullet", SpriteConstants.FIRE)
        );


        wave[4].createWave(enemiesToAdd[4], player);
        waves.add(wave[4]);

        //wave 5
        Fire staticShootFire1 = new Fire(width * 0.15f, height * 0.9f, 0, 0, 1000,
		SpriteConstants.ENEMY_BULLET, "EnemyBullet", SpriteConstants.FIRE);
		staticShootFire1.changeState(new StaticShootState(staticShootFire1));
        enemiesToAdd[5].add(staticShootFire1);
        Fire staticShootFire2 = new Fire(width * 0.85f, height * 0.9f, 0, 0, 1000,
                SpriteConstants.ENEMY_BULLET, "EnemyBullet", SpriteConstants.FIRE);
        staticShootFire2.changeState(new StaticShootState(staticShootFire2));
        enemiesToAdd[5].add(staticShootFire2);

        wave[5].createWave(enemiesToAdd[5], player);
        waves.add(wave[5]);


            totalWaves = 10;

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

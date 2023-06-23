package com.mygdx.game.scenes;

import com.mygdx.game.enemy.Bull;
import com.mygdx.game.enemy.Enemy;
import com.mygdx.game.enemy.Fire;

import java.util.ArrayList;

public class WavesStage1 extends WaveManager{
    private Wave []wave = new Wave[15];
    private ArrayList<Enemy> enemiesToAdd = new ArrayList<Enemy>();
    @Override
    public void createWaves() {
        enemiesToAdd.add(
                new Bull(0, height - 1, 700, 700, 1000,
                        "Bullets/EnemyBullet.png", "EnemyBullet", "Enemies/bull.png")
        );
        wave[1].createWave(enemiesToAdd);
        waves.add(wave[1]);

    }
}

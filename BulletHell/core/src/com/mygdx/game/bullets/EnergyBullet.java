package com.mygdx.game.bullets;

public class EnergyBullet extends SimpleBullet{
    EnergyBullet(float positionX, float positionY, float sizeX, float sizeY) {
        super(positionX, positionY, sizeX, sizeY);
        super.setBulletSpeed(750);
    }
}

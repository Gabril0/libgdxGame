package com.mygdx.game.bullets;

public class SlowBullet extends SimpleBullet{
    SlowBullet(float positionX, float positionY, float sizeX, float sizeY) {
        super(positionX, positionY, sizeX, sizeY);
        setBulletSpeed(50f);
    }
}

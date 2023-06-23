package com.mygdx.game.bullets;

public class TransformationBullet extends SimpleBullet{
    TransformationBullet(float positionX, float positionY, float sizeX, float sizeY) {
        super(positionX, positionY, sizeX, sizeY);
        super.setBulletSpeed(800);
    }
}

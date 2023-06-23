package com.mygdx.game.bosses;

public class SpaceMan extends BossFundamentals{

    public SpaceMan(float positionX, float positionY, float speedX, float speedY, float health, String bulletImg, String bulletType, String sprite) {
        super(positionX, positionY, speedX, speedY, health, bulletImg, bulletType, sprite);
        super.bulletPool.setCoolDown(1f);
        super.damage = 300;
        super.stationaryTime = 1f;
        super.moveDuration = 0.2f;
        super.bulletPool.changeSize(10000);
    }

}

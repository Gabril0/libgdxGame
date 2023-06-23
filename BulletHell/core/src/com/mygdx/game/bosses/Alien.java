package com.mygdx.game.bosses;

public class Alien extends BossFundamentals{
    public Alien(float positionX, float positionY, float speedX, float speedY, float health, String bulletImg, String bulletType, String sprite) {
        super(positionX, positionY, speedX, speedY, health, bulletImg, bulletType, sprite);
        damage = 100;
        bulletPool.changeSize(220);
        bulletPool.setCoolDown(0.1f);
    }
}

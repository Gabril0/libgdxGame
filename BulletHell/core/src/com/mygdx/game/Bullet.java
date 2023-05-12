package com.mygdx.game;

public interface Bullet {
    public void createBullet(String imgPath);
    public void renderBullet(float positionX, float positionY);
    public void disposeBullet();
    public void move();
    public void activate();
    public void deactivate();
    public boolean getIsActive();
}

package com.mygdx.game;

public interface Bullet {
    public void createBullet();
    public void renderBullet();
    public void disposeBullet();
    public void move();
    public void activate();
    public void deactivate();
    public boolean getIsActive();
}

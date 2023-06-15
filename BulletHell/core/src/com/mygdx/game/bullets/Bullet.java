package com.mygdx.game.bullets;

import com.badlogic.gdx.math.Polygon;

public interface Bullet {
    public void createBullet(String imgPath);
    public void renderBullet(float positionX, float positionY, float playerSizeX, float playersizeY, float playerRotation);
    public void disposeBullet();
    public void move();
    public void activate();
    public void deactivate();
    public boolean getIsActive();
    public Polygon getCollider();
    public Bullet getBullet();
}

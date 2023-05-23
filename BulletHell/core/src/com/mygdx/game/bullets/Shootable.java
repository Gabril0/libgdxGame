package com.mygdx.game.bullets;

import com.badlogic.gdx.math.Polygon;

public interface Shootable {
    public boolean isAlive();
    public Polygon getCollider();
    public void checkCollision(Shootable shootable);
    public void setHealth(float damage);

}

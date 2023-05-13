package com.mygdx.game.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Enemy {
    private Sprite sprite;
    private Texture img;

    private int width, height;
    private int positionX, positionY;

    public Enemy() {
        width = 64;
        height = 64;
        positionX = 0;
        positionY = 0;

        img = new Texture("badlogic.jpg");
        sprite = new Sprite(img, positionX, positionY, width, height);
    }

    public Sprite getSprite() {
        return sprite;
    }
    public int getPositionX() {
        return positionX;
    }
    public int getPositionY() {
        return positionY;
    }

}

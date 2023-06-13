package com.mygdx.game.backgroundAndCursor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {
    private float sizeX, sizeY;
    private Texture imgFast;
    private Texture imgSlow;
    private SpriteBatch batch;
    private float positionSlowX = 0, positionSlowY = 0;
    private float positionFastX = 0, positionFastY = 0;
    private float positionEffectX = 0, positionEffectY = 0;
    private Texture opacityEffect;
    private float bounds;
    private float scrollSpeed = 10;

    public void createBackground(float sizeX, float sizeY, String pathSlow, String pathFast) {
        batch = new SpriteBatch();
        imgSlow = new Texture(pathSlow);
        imgFast = new Texture(pathFast);
        opacityEffect = new Texture("Backgrounds/opacityEffect.png");
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        bounds = -sizeX / 2.05f; //this is because of the conversion when getting players monitor size
    }

    public void renderBackground() {
        move();
        batch.begin();
        batch.draw(imgSlow, positionSlowX, positionSlowY, sizeX, sizeY);
        batch.draw(imgFast, positionFastX, positionFastY, sizeX / 2, sizeY);
        batch.draw(opacityEffect, positionEffectX, positionEffectY, sizeX, sizeY);
        batch.end();
    }

    public void disposeBackground() {
        imgSlow.dispose();
        imgFast.dispose();
    }

    public void move() {
        if (positionSlowX >= bounds) {
            positionSlowX -= scrollSpeed * Gdx.graphics.getDeltaTime();
        }
        if (positionFastX >= bounds/2) {
            positionFastX -= scrollSpeed/2 * Gdx.graphics.getDeltaTime();
        }

    }
}
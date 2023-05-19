package com.mygdx.game.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemy {
    private SpriteBatch batch;
    private Texture img;

    private int width, height;
    private float positionX, positionY;
    private float speedX, speedY;

    public Enemy() {
        width = 64;
        height = 64;
        positionX = 0;
        positionY = 0;
        speedX = 300f;
        speedY = 300f;

        img = new Texture("PlayerTransformation.png");
        batch = new SpriteBatch();
    }

    public void renderEnemy() {
        randomMove();
        checkBounds();
        batch.begin();
        batch.draw(img, positionX, positionY, width, height);
        batch.end();
    }

    private void checkBounds(){ //checks if the player still on bounds
        if (positionX < 0) positionX = 0;
        if (positionY < 0) positionY = 0;
        if (positionX > Gdx.graphics.getWidth() - width) positionX = Gdx.graphics.getWidth() - width;
        if (positionY > Gdx.graphics.getHeight() - height) positionY = Gdx.graphics.getHeight() - height;

    }


    private void randomMove() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        positionX += deltaTime * speedX * 1;
        positionY += deltaTime * speedY * 1;
    }

    public void disposeEnemy() {
        img.dispose();
        batch.dispose();
    }

    public float getPositionX() {
        return positionX;
    }
    public float getPositionY() {
        return positionY;
    }

}
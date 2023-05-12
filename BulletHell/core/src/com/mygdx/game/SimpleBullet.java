package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SimpleBullet implements Bullet {
    private boolean isActive = false; //checks if the bullet is active in the bullet pool, initialize as inactive
    private Texture texture;
    private SpriteBatch spriteBatch;
    private float positionX, positionY; //for the position manipulation of the bullet
    private float sizeX, sizeY; //for the size 
    private float bulletSpeed = 1;

    SimpleBullet(float positionX, float positionY){ //Constructor to get players position
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void createBullet(String imgPath){ //creating the bullet
        spriteBatch = new SpriteBatch();
        texture = new Texture(imgPath);
    }

    public void renderBullet(float positionX, float positionY){ //rendering and moving
        this.positionX = positionX;
        this.positionY = positionY;
        move();
        spriteBatch.begin();
        spriteBatch.draw(texture, positionX, positionY, sizeX, sizeY);
        spriteBatch.end();
    }

    public void disposeBullet(){ //freeing memory
        spriteBatch.dispose();
        texture.dispose();
    }


    public void move() {
        positionX = positionX * bulletSpeed *  Gdx.graphics.getDeltaTime();
    }


    public void activate() { //activation method for the list
        isActive = true;
    }


    public void deactivate() { //deactivation method for the list
        isActive = false;
    }

    public boolean getIsActive(){ //to check whatever if a bullet is active or not
        return isActive;
    }
    
}

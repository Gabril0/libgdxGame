package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SimpleBullet implements Bullet {
    private boolean isActive = false; //checks if the bullet is active in the bullet pool, initialize as inactive
    private Texture texture;
    private SpriteBatch spriteBatch;
    private float positionX, positionY; //for the position manipulation of the bullet
    private float sizeX = 32, sizeY = 32; //for the size 
    private float bulletSpeed = 200;

    private int guarantee = 1;

    SimpleBullet(float positionX, float positionY){ //Constructor to get players position
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void createBullet(String imgPath){ //creating the bullet
        spriteBatch = new SpriteBatch();
        texture = new Texture(imgPath);
    }

    public void renderBullet(float positionX, float positionY){ //rendering and moving
        if(guarantee == 1){ //guaranteed to run once to get the player position
            this.positionX = positionX;
            this.positionY = positionY;
        }
        guarantee = 0;
        move();
        spriteBatch.begin();
        spriteBatch.draw(texture, this.positionX, this.positionY, sizeX, sizeY);
        spriteBatch.end();
    }

    public void disposeBullet(){ //freeing memory
        spriteBatch.dispose();
        texture.dispose();
    }


    public void move() {
        positionX += bulletSpeed *  Gdx.graphics.getDeltaTime();
    }


    public void activate() { //activation method for the list
        isActive = true;
    }


    public void deactivate() { //deactivation method for the list
        isActive = false;
        guarantee = 1; //resets the guarantee to get the player position once is activated again
    }

    public boolean getIsActive(){ //to check whatever if a bullet is active or not
        return isActive;
    }
    
}

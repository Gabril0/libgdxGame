package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Input;

public class Player {
    private SpriteBatch batch; 
	private Texture img;

    private float spriteSizeX = 64, spriteSizeY = 64; //float for the size
    private float spritePositionX = 0f, spritePositionY = 0f; //float for the position
    private float speedX = 300f, speedY = 300f; //float for the speed

    public void createPlayer() { //do these actions once the game starts
        batch = new SpriteBatch();
		img = new Texture("PlayerBaseSprite.png");
    }
    public void renderPlayer() { //do these actions every frame
        movePlayer();
        checkBounds();
        drawPlayer();
    }
    public void disposePlayer() { //dispose of the player resources onde the game is closed
        batch.dispose();
		img.dispose();
    }

    private void movePlayer(){
        float deltaTime = Gdx.graphics.getDeltaTime();
        spritePositionX += deltaTime * speedX * horizontalMovement();
        spritePositionY += deltaTime * speedY * verticalMovement();
    }

    private void drawPlayer(){ //Draws the player sprite each frame
        batch.begin();
		batch.draw(img, spritePositionX, spritePositionY, spriteSizeX, spriteSizeY);
		batch.end();
    }

    private void checkBounds(){ //checks if the player still on bounds
        if (spritePositionX < 0) spritePositionX = 0;
        if (spritePositionY < 0) spritePositionY = 0;
        if (spritePositionX > 1300) spritePositionX = 1300;
        if (spritePositionY > 702) spritePositionY = 702;

    }

    private int horizontalMovement(){
        boolean left = Gdx.input.isKeyPressed(Input.Keys.A);  //gets the input for left movement
        boolean right = Gdx.input.isKeyPressed(Input.Keys.D); //gets the input for right movement
        //atributions
        if(left && !right) return -1;
        if(!left && right) return 1;
        return 0;

    }

    private int verticalMovement(){
        boolean up = Gdx.input.isKeyPressed(Input.Keys.W);  //gets the input for up movement
        boolean down = Gdx.input.isKeyPressed(Input.Keys.S); //gets the input for down movement
        //atributions
        if(up && !down) return 1;
        if(!up && down) return -1;
        return 0;

    }

}
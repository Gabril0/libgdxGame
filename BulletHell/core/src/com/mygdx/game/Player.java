package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Array;

public class Player {
    private SpriteBatch batch; 
	private Texture img;

    private float spriteSizeX = 64, spriteSizeY = 64; //float for the size
    private float spritePositionX = 0f, spritePositionY = 0f; //float for the position
    private float speedX = 450f, speedY = 450f; //float for the speed

    private Array<Texture> shootAnimation;
    private int currentFrameIndex;
    private float frameDuration = 0.1f; //duration of animation speed
    private float frameTimer = 0f;
    private boolean isShooting;
    public void createPlayer() { //do these actions once the game starts
        batch = new SpriteBatch();
		img = new Texture("PlayerBaseSprite.png");

        //loading animation "shoot"
        shootAnimation = new Array<Texture>();
        shootAnimation.add(new Texture("animationShoot2outOf3.png"));
        shootAnimation.add(new Texture("animationShoot3outOf3.png"));
        shootAnimation.add(new Texture("PlayerBaseSprite.png"));

    }
    public void renderPlayer(boolean isShooting) { //do these actions every frame
        this.isShooting = isShooting;
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

        if(isShooting){ //shooting animation

            frameTimer += Gdx.graphics.getDeltaTime(); //getting the time from the beginning of animation
            if (frameTimer >= frameDuration) {
                frameTimer = 0f;
                currentFrameIndex = (currentFrameIndex + 1) % shootAnimation.size; //calls next frame
            }

            Texture currentFrame = shootAnimation.get(currentFrameIndex);//draw the current frame
            batch.draw(currentFrame, spritePositionX, spritePositionY, spriteSizeX / 2, spriteSizeY / 2, spriteSizeX,
                    spriteSizeY, 1f, 1f, rotateToCursor(), 0, 0, img.getWidth(),
                    img.getHeight(), false, false);
        }
        else{ //idle
		    batch.draw(img, spritePositionX, spritePositionY, spriteSizeX / 2, spriteSizeY / 2, spriteSizeX,
            spriteSizeY, 1f, 1f, rotateToCursor(), 0, 0, img.getWidth(),
            img.getHeight(), false, false); //Giant constructor because the framework doesn`t accept it any shorter
        }
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

    public float getSpritePositionX() {
        return spritePositionX;
    }
    public float getSpritePositionY() {
        return spritePositionY;
    }
    public float getSpriteSizeX() {
        return spriteSizeX;
    }
    public float getSpriteSizeY() {
        return spriteSizeY;
    }

    public float rotateToCursor(){ //Rotates player sprite to the cursor position
        float mouseX = Gdx.input.getX(); //gets mouse position X
        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY(); //gets mouse position Y
        //The getHeight is because libgdx uses bottomLeft starting position, so this is a conversion to their system

        float angleRad = (float) Math.atan2(mouseY - (spritePositionY+(spriteSizeY/2)), mouseX - (spritePositionX + (spriteSizeX/2))); //gets the rad angles
        float angleDeg = (float) Math.toDegrees(angleRad); //converts it to degrees
        return angleDeg + 90;
    }

    public void attackAnimation(){

    }

}
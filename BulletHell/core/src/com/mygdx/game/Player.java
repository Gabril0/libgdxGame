package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

public class Player {

    //Player atributes
    private float health = 1000;
    private float damage = 100;

    //Base sprite variables
    private SpriteBatch batch; 
	private Texture img;

    private ShapeRenderer collision;

    //Movement and sizing
    private float spriteSizeX = 64, spriteSizeY = 64; //float for the size
    private float spritePositionX = 0f, spritePositionY = 0f; //float for the position
    private float speedX = 450f, speedY = 450f; //float for the speed

    //Animations
    private Animation shootingAnimation;

    //Booleans
    private boolean isShooting;
    public void createPlayer() { //do these actions once the game starts

        //base player
        batch = new SpriteBatch();
		img = new Texture("PlayerBaseSprite.png");
        collision = new ShapeRenderer();

        //shooting animation
        shootingAnimation = new ShootingAnimation();
        shootingAnimation.create();
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
        shootingAnimation.dispose();
    }

    private void movePlayer(){
        float deltaTime = Gdx.graphics.getDeltaTime();
        spritePositionX += deltaTime * speedX * horizontalMovement();
        spritePositionY += deltaTime * speedY * verticalMovement();
    }

    private void drawPlayer(){ //Draws the player sprite each frame
        batch.begin();

        if(isShooting){ //shooting animation
            shootingAnimation.render( spritePositionX, spritePositionY, spriteSizeX,
                    spriteSizeY, rotateToCursor(), batch);

        }
        else{ //idle
		    batch.draw(img, spritePositionX, spritePositionY, spriteSizeX / 2, spriteSizeY / 2, spriteSizeX,
            spriteSizeY, 1f, 1f, rotateToCursor(), 0, 0, img.getWidth(),
            img.getHeight(), false, false); //Giant constructor because the framework doesn`t accept it any shorter
        }
        batch.end();

        collision.begin(ShapeRenderer.ShapeType.Line);
        collision.identity(); // Reset the transformation matrix
        collision.translate(spritePositionX + spriteSizeX / 2, spritePositionY + spriteSizeY / 2, 0); // Translate to the player's center
        collision.rotate(0, 0, 1, rotateToCursor()); // Rotate around the player's center
        collision.rect(-spriteSizeX / 4, -spriteSizeY / 4, spriteSizeX/2, spriteSizeY/2);
        collision.end();
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
}
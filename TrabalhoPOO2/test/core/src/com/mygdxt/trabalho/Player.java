package com.mygdxt.trabalho;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Input;

public class Player {
	private boolean isOnGround;

	private ShapeRenderer shapeRenderer;
	private Texture playerSprite;
	private SpriteBatch batch;

	private Rectangle collider;

	private float positionX = 200, positionY = 300; // gets the current position of the sprite
	private float lastPositionX, lastPositionY; // gets the current position of the sprite
	private float xSpeed = 400, ySpeed = 10000; // speed for the sprite to move around
	private int sizeWidth = 50, sizeHeight = 50;

	public void createPlayer() { // method to get player visual stuff
		shapeRenderer = new ShapeRenderer(); // Creates a shape renderer object
		batch = new SpriteBatch(); // Creates a batch
		playerSprite = new Texture(Gdx.files.internal("placeholder.png")); // Creates and gets player sprite png
	}

	public void renderPlayer() { // players action
		move();

	}

	public void disposePlayer() { // is called to discard player assets
		batch.dispose();
		playerSprite.dispose();
		shapeRenderer.dispose();
	}

	private void move(){
		float deltaTime = Gdx.graphics.getDeltaTime(); // casting of the deltaTime

		if(!isOnGround){
			positionY -= 1; //gravity simulation
		}

		lastPositionX = positionX;
		lastPositionY = positionY;
		
		positionX += xSpeed * horizontalMovement() * deltaTime; //increments the speed

		if(isOnGround){
			positionY += ySpeed * verticalMovement() * deltaTime; //increments the speed
			isOnGround = false;
		}

		batch.begin(); //Rendering of the sprite
		batch.draw(playerSprite, positionX, positionY, sizeWidth, sizeHeight);
		batch.end();

		collider = new Rectangle(positionX, positionY, sizeWidth, sizeHeight);
	}

	private int verticalMovement() { // check the vertical movement input

		if (Gdx.input.isKeyPressed(Input.Keys.W)) { // goes up
			return 1;
		}

		// if (Gdx.input.isKeyPressed(Input.Keys.S)) { // goes down
		// 	return -1;
		// }
		return 0; // stand still
	}

	private int horizontalMovement() { // same as verticalMovement

		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			return 1;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			return -1;
		}
		return 0;
	}

	public float getLastPositionX() {
		return lastPositionX;
	}

	public float getLastPositionY() {
		return lastPositionY;
	}

	public void touchGround() {
		isOnGround = true;
		positionY = lastPositionY;
	}
	public void touchWall() {
		isOnGround = true;
		positionX = lastPositionX;
	}
	public void touchCeiling() {
		isOnGround = false;
		positionY = lastPositionY;
	}

	public void playerCantRun(){
		positionX = lastPositionX;
	}

	public Rectangle getCollider() {
		return this.collider;
	}
}

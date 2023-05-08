



package com.mygdxt.trabalho;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Input;

public class Player {
	private boolean isOnGround;

	private ShapeRenderer shapeRenderer;
	private Texture playerSprite;
	private SpriteBatch batch;

	private Rectangle collider;
	private Vector2 position;
	private float positionX = 200f, positionY = 300f; // gets the current position of the sprite
	private float lastPositionInX, lastPositionInY; // gets the current position of the sprite
	private float xSpeed = 400f, ySpeed = 1000000f; // speed for the sprite to move around
	private int sizeWidth = 50, sizeHeight = 50;

	private float jumpDuration = 0.5f;


	private float gravity = 2;

	public void createPlayer() { // method to get player visual stuff
		shapeRenderer = new ShapeRenderer(); // Creates a shape renderer object
		batch = new SpriteBatch(); // Creates a batch
		playerSprite = new Texture(Gdx.files.internal("placeholder.png")); // Creates and gets player sprite png
		position = new Vector2(positionX, positionY);
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
		float desiredPositionX = position.x += xSpeed * horizontalMovement() * deltaTime;
		
		
		if(!isOnGround){
			position.y -= gravity; //gravity simulation
		}
		
		lastPositionInX = position.x;
		lastPositionInY = position.y;
	
		
		if(isOnGround){
			float desiredPositionY = position.y += ySpeed * verticalMovement() * deltaTime;
			float alpha = Math.min(deltaTime/jumpDuration, 1);
			position.y = Interpolation.linear.apply(lastPositionInY, desiredPositionY, alpha);
			//position.interpolate(position, desiredPositionY, Interpolation.linear);
			isOnGround = false;
		}

		batch.begin(); //Rendering of the sprite
		//position.interpolate(position, desiredPositionX, Interpolation.linear);
		batch.draw(playerSprite, position.x, position.y, sizeWidth, sizeHeight);
		batch.end();
		collider = new Rectangle(position.x, position.y, sizeWidth, sizeHeight);


	}

	private int verticalMovement() { // check the vertical movement input

		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) { // goes up
			isOnGround = false;
			return 1;
		}

		// if (Gdx.input.isKeyPressed(Input.Keys.S)) { // goes down
		// 	return -1;
		// }
		return 0; // stand still
	}

	private int horizontalMovement() { // same as verticalMovement
		boolean pressedD = Gdx.input.isKeyPressed(Input.Keys.D);
		boolean pressedA = Gdx.input.isKeyPressed(Input.Keys.A);
		if (pressedD && !pressedA) {
			return 1;
		}

		if (pressedA && !pressedD) {
			return -1;
		}
		return 0;
	}

	public float getlastPositionInX() {
		return lastPositionInX;
	}

	public float getlastPositionInY() {
		return lastPositionInY;
	}

	public void touchGround() {
		isOnGround = true;
		position.y = lastPositionInY;
	}
	public void touchWall() {
		isOnGround = true;
		position.x = lastPositionInX;
	}
	public void touchCeiling() {
		isOnGround = false;
		position.y = lastPositionInY;
	}

	public void playerCantRun(){
		position.x = lastPositionInX;
	}

	public Rectangle getCollider() {
		return this.collider;
	}
}

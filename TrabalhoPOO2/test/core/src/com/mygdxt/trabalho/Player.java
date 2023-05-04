package com.mygdxt.trabalho;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import com.badlogic.gdx.Input;

public class Player{
	private ShapeRenderer shapeRenderer;
	private Texture playerSprite;
	private SpriteBatch batch;
	private World world; //for the physics

	private float positionX = 100, positionY = 100; //gets the current position of the sprite
	private float xSpeed = 400, ySpeed = 400; //speed for the sprite to move around
	private int sizeWidth = 50, sizeHeight = 50;
	
	public void createPlayer () { //method to get player visual stuff
		world = new World(new Vector2(0, -10), true);
		shapeRenderer = new ShapeRenderer(); //Creates a shape renderer object
		batch = new SpriteBatch(); //Creates a batch
		playerSprite = new Texture(Gdx.files.internal("placeholder.png")); //Creates and gets player sprite png
	}

	public void renderPlayer () { //players action
		move();

	}
	
	public void disposePlayer () { //is called to discard player assets
		batch.dispose();
		playerSprite.dispose();
		shapeRenderer.dispose();
	}

	private void move(){
		float deltaTime = Gdx.graphics.getDeltaTime(); // casting of the deltaTime

		world.step(deltaTime, 6,2); //updating world physics

		positionY -= 1; //gravity simulation

		positionX += xSpeed * horizontalMovement() * deltaTime; //increments the speed
		positionY += ySpeed * verticalMovement() * deltaTime; //increments the speed
		
		batch.begin(); //Rendering of the sprite
		batch.draw(playerSprite, positionX, positionY, sizeWidth, sizeHeight);
		batch.end();
	}

	private int verticalMovement(){ //check the vertical movement input
		
		if(Gdx.input.isKeyPressed(Input.Keys.W)){ //goes up
			return 1;
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.S)){ //goes down
			return -1;
		}
		return 0; //stand still
	}

	private int horizontalMovement(){ //same as verticalMovement
		
		if(Gdx.input.isKeyPressed(Input.Keys.D)){
			return 1;
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.A)){
			return -1;
		}
		return 0;
	}
}


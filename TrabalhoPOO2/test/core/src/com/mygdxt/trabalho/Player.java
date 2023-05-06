package com.mygdxt.trabalho;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdxt.trabalho.physics.PhysicsWorld;
import com.mygdxt.trabalho.physics.PlayerPhysics;
import com.badlogic.gdx.Input;

public class Player {
	private Texture playerTexture;
	private SpriteBatch batch;
	private Sprite playerSprite;

	private PlayerPhysics physics;
	private final float PIXELS_PER_METER = 920f;	

	public void createPlayer (PhysicsWorld world) { //method to get player visual stuff
		batch = new SpriteBatch(); //Creates a batch
		playerTexture = new Texture(Gdx.files.internal("placeholder.png")); //Creates and gets player sprite png
		physics = new PlayerPhysics(world, 0, 0); // creates the physics object that handles all of the player's physics

		// create and set the player sprite, so its equal the physics object
		playerSprite = new Sprite(playerTexture);
		// makes the sprite the same size as the physic entity
		playerSprite.setSize(physics.getWidth() * PIXELS_PER_METER, physics.getHeight() * PIXELS_PER_METER);
		// (hopefully) makes the sprite have the same origin as the physic entity
		playerSprite.setOrigin(playerSprite.getWidth() / 2, playerSprite.getHeight() / 2);
		// makes the sprite have the same position as the physic entity converting units to pixels
		playerSprite.setPosition(physics.getPosition().x * PIXELS_PER_METER, physics.getPosition().y * PIXELS_PER_METER);
	}

	public void renderPlayer () { //players action
		move();

	}
	
	public void disposePlayer () { //is called to discard player assets
		batch.dispose();
		playerTexture.dispose();
	}

	private void move(){
		float deltaTime = Gdx.graphics.getDeltaTime(); // casting of the deltaTime

		// positionY += ySpeed * verticalMovement() * deltaTime; //increments the speed

		horizontalMovement();
		// (hopefully) updates the player sprite position
		playerSprite.setPosition(physics.getPosition().x * PIXELS_PER_METER, physics.getPosition().y * PIXELS_PER_METER);
		
		batch.begin(); //Rendering of the sprite
		playerSprite.draw(batch);
		batch.end();
	}

	// private int verticalMovement(){ //check the vertical movement input
	// 	//saves the key press in a variable
	// 	boolean up = Gdx.input.isKeyPressed(Input.Keys.W);
	// 	boolean down = Gdx.input.isKeyPressed(Input.Keys.S);


	// 	if(up && !down){ //goes up
	// 		return 1;
	// 	}
		
	// 	if(down && !up){ //goes down
	// 		return -1;
	// 	}
	// 	return 0; //stand still
	// }

	private void horizontalMovement(){ //same as verticalMovement
		//saves the key press in a variable
		boolean left = Gdx.input.isKeyPressed(Input.Keys.A);
		boolean right = Gdx.input.isKeyPressed(Input.Keys.D);

		if(right && !left){
			physics.applyForceX(2f);
		}
		
		if(left && !right){
			physics.applyForceX(-2f);
		}
	}
}



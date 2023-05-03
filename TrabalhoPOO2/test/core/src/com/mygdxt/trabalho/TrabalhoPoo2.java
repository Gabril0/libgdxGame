package com.mygdxt.trabalho;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Input;

public class TrabalhoPoo2 extends ApplicationAdapter {
	ShapeRenderer shapeRenderer;

	private float circleX = 100, circleY = 100; //gets the current position of the sprite
	private float xSpeed = 400, ySpeed = 400; //speed for the sprite to move around
	
	@Override
	public void create () { //runs everytime the program is started
		shapeRenderer = new ShapeRenderer(); //Creates a shape renderer object
	}

	@Override
	public void render () { //just like update in unity, runs every frame
		circleX += xSpeed * horizontalMovement() * Gdx.graphics.getDeltaTime(); //increments the speed
		circleY += ySpeed * verticalMovement() * Gdx.graphics.getDeltaTime(); //increments the speed

		ScreenUtils.clear(98f/255f, 112f/255f, 127f/255f, 1); //Set the background color
		

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled); //begin the shape rendering
		shapeRenderer.setColor(255f/255f,255f/255f,255f/255f,1); //sets the color of the shape
		shapeRenderer.circle(circleX,circleY,75); //set the type and size of the circle
		shapeRenderer.end(); //ends the rendering
	}
	
	@Override
	public void dispose () { //is called when the program is closed
		shapeRenderer.dispose();
	}

	private int verticalMovement(){
		
		if(Gdx.input.isKeyPressed(Input.Keys.W)){
			return 1;
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.S)){
			return -1;
		}
		return 0;
	}

	private int horizontalMovement(){
		
		if(Gdx.input.isKeyPressed(Input.Keys.D)){
			return 1;
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.A)){
			return -1;
		}
		return 0;
	}
}

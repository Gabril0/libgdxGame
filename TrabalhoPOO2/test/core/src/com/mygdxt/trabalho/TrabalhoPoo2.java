package com.mygdxt.trabalho;

import java.util.ArrayList;

// This class acts like a game manager, rendering the gameObjects and disposing them

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;

public class TrabalhoPoo2 extends ApplicationAdapter {
	Player player = new Player();
	ArrayList<Ground> groundListLevel1 =  new ArrayList<Ground>();
	Ground tile1 = new Ground(200,200,"groundPlaceHolder.png");
	Ground tile2 = new Ground(232,200,"groundPlaceHolder.png");
	Ground tile3 = new Ground(264,200,"groundPlaceHolder.png");
	Ground tile4 = new Ground(296,200,"groundPlaceHolder.png");
	Ground tile5 = new Ground(328,200,"groundPlaceHolder.png");
	Ground tile6 = new Ground(200,328,"groundPlaceHolder.png");
	
	
	
	@Override
	public void create () { //runs everytime the program is started
		player.createPlayer();
		groundListLevel1.add(tile1);
		groundListLevel1.add(tile2);
		groundListLevel1.add(tile3);
		groundListLevel1.add(tile4);
		groundListLevel1.add(tile5);
		groundListLevel1.add(tile6);
		for (Ground groundTest : groundListLevel1) {
			groundTest.createGround();
		}
	}

	@Override
	public void render () { //just like update in unity, runs every frame
		ScreenUtils.clear(98f/255f, 112f/255f, 127f/255f, 1); //Set the background color
		player.renderPlayer();
		for (Ground groundTest : groundListLevel1) {
		groundTest.renderGround();
		}
		checkOverlap();
	}
	
	@Override
	public void dispose () { //is called when the program is closed
		player.disposePlayer();
		for (Ground groundTest : groundListLevel1) {
			groundTest.disposeGround();
		}
	}

	private void checkOverlap(){
		for (Ground groundTest : groundListLevel1) {
			
			boolean playerTouchingGround =  player.getCollider().overlaps(groundTest.getColliderTop());
			boolean playerTouchingWalls = player.getCollider().overlaps(groundTest.getColliderSide());
			boolean playerTouchingCeiling = player.getCollider().overlaps(groundTest.getColliderBottom());
			if(playerTouchingGround){
				player.touchGround();
			}
			if(playerTouchingWalls){
				player.touchWall();
			}
			if(playerTouchingCeiling){
				player.touchCeiling();
			}
		}
	}
}

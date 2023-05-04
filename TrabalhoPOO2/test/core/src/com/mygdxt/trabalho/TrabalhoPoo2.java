package com.mygdxt.trabalho;

// This class acts like a game manager, rendering the gameObjects and disposing them

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class TrabalhoPoo2 extends ApplicationAdapter {
	Player player = new Player();
	OrthographicCamera cam = new OrthographicCamera();
	
	@Override
	public void create () { //runs everytime the program is started
		player.createPlayer();
	}

	@Override
	public void render () { //just like update in unity, runs every frame
		ScreenUtils.clear(98f/255f, 112f/255f, 127f/255f, 1); //Set the background color
		player.renderPlayer();
	}
	
	@Override
	public void dispose () { //is called when the program is closed
		player.disposePlayer();
	}

}

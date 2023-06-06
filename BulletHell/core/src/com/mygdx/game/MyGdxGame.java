package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.backgroundAndCursor.CustomCursor;
import com.mygdx.game.scenes.Stage1;

public class MyGdxGame extends ApplicationAdapter {
	int currentStage = 1;
	CustomCursor cursor = new CustomCursor();
	Stage1 s;
	
	@Override
	public void create () {
		cursor.create();
		if(currentStage == 1) {
			s = new Stage1(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
			s.create();}

	}

	@Override
	public void render () {
		s.render();
	}
	
	@Override
	public void dispose () {
		cursor.dispose();
		s.dispose();
	}
}
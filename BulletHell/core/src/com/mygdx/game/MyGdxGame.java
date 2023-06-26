package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.backgroundAndCursor.CustomCursor;
import com.mygdx.game.scenes.Stage1;
import com.mygdx.game.scenes.Stage2;

public class MyGdxGame extends ApplicationAdapter {
	int currentStage = 1;
	CustomCursor cursor = new CustomCursor();
	Stage1 s1;
	Stage2 s2;
	
	@Override
	public void create () {
		cursor.create();

		s1 = new Stage1(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
		s1.create();
		s2 = new Stage2(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
		s2.create();

	}

	@Override
	public void render () {
//		currentStage = 2;
		if(currentStage == 1) {
			s1.render();
		}
		if(s1.isOver()) {
			currentStage = 2;
			s1.setIsOver(false);
		}
		if(currentStage == 2){
			s2.render();
		}
		if(s2.isOver()) {
			currentStage = 3;
			s2.setIsOver(false);
		}

	}
	
	@Override
	public void dispose () {
		cursor.dispose();
		s1.dispose();
		s2.dispose();
	}
}
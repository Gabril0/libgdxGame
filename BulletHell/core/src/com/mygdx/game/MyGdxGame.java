package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	Player player = new Player();
	
	@Override
	public void create () {
		player.createPlayer();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);
		player.renderPlayer();
	}
	
	@Override
	public void dispose () {
		player.disposePlayer();
	}
}

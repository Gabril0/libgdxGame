package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	Player player = new Player();
	BulletPool bulletPool = new BulletPool(50);

	
	@Override
	public void create () {
		bulletPool.createBulletPool("PlayerBullet.png");
		player.createPlayer();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);
		player.renderPlayer();
		bulletPool.renderBulletPool(player.getSpritePositionX(), player.getSpritePositionY());
	}
	
	@Override
	public void dispose () {
		player.disposePlayer();
		bulletPool.disposeBulletPool();
	}
}
package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	Player player = new Player();
	BulletPool bulletPool = new BulletPool(50);
	CustomCursor cursor = new CustomCursor();

	
	@Override
	public void create () {
		cursor.create();
		bulletPool.createBulletPool("PlayerBullet.png");
		player.createPlayer();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);
		player.renderPlayer(bulletPool.getIsShooting());
		bulletPool.renderBulletPool(player.getSpritePositionX(), player.getSpritePositionY(), 
		player.getSpriteSizeX(), player.getSpriteSizeY(), player.rotateToCursor() - 90); //-op because bullets are in a diferent orientation
	}
	
	@Override
	public void dispose () {
		cursor.dispose();
		player.disposePlayer();
		bulletPool.disposeBulletPool();
	}
}
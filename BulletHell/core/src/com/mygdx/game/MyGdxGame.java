package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.enemy.Enemy;

public class MyGdxGame extends ApplicationAdapter {
	Player player = new Player();
	BulletPool bulletPool = new BulletPool(50);
	CustomCursor cursor = new CustomCursor();
	Enemy e;
	Background bg = new Background();

	
	@Override
	public void create () {
		bg.createBackground(Gdx.graphics.getWidth()*1.96f, Gdx.graphics.getHeight(), "map1Slow.png", "map1Fast.png");
		cursor.create();
		e = new Enemy();
		bulletPool.createBulletPool("PlayerBullet.png");
		player.createPlayer();
		
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);
		bg.renderBackground();
		player.renderPlayer(bulletPool.getIsShooting());
		bulletPool.renderBulletPool(player.getSpritePositionX(), player.getSpritePositionY(), 
		player.getSpriteSizeX(), player.getSpriteSizeY(), player.rotateToCursor() - 90); //-op because bullets are in a diferent orientation
		e.renderEnemy();
	}
	
	@Override
	public void dispose () {
		bg.disposeBackground();
		cursor.dispose();
		player.disposePlayer();
		bulletPool.disposeBulletPool();
		e.disposeEnemy();
	}
}
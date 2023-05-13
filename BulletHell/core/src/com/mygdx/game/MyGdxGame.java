package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.enemy.Enemy;

public class MyGdxGame extends ApplicationAdapter {
	Player player = new Player();
	BulletPool bulletPool = new BulletPool(50);
	SpriteBatch batch;
	Enemy e = new Enemy();
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		bulletPool.createBulletPool("PlayerBullet.png");
		player.createPlayer();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);
		player.renderPlayer();
		batch.begin();
		//batch.draw(e.getSprite(), e.getPositionX(), e.getPositionY());
		batch.end();
		bulletPool.renderBulletPool(player.getSpritePositionX(), player.getSpritePositionY());
	}
	
	@Override
	public void dispose () {
		player.disposePlayer();
		bulletPool.disposeBulletPool();
	}
}
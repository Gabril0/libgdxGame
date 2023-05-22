package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.backgroundAndCursor.Background;
import com.mygdx.game.backgroundAndCursor.CustomCursor;
import com.mygdx.game.bosses.BossFundamentals;
import com.mygdx.game.bullets.BulletPool;
import com.mygdx.game.enemy.Enemy;
import com.mygdx.game.listeners.EventManager;
import com.mygdx.game.player.Player;

public class MyGdxGame extends ApplicationAdapter {
	Player player = new Player();
	BulletPool bulletPool = new BulletPool(50);
	CustomCursor cursor = new CustomCursor();
	Enemy e;
	Background bg = new Background();
	EventManager em = new EventManager();
	BossFundamentals miniBoss = new BossFundamentals();

	
	@Override
	public void create () {
		bg.createBackground(Gdx.graphics.getWidth()*1.96f, Gdx.graphics.getHeight(), "map1Slow.png", "map1Fast.png");
		cursor.create();
		e = new Enemy();
		bulletPool.createBulletPool("PlayerBullet.png");
		player.createPlayer();
		em.addShotListener(player);
		miniBoss.createBoss("Satellite.png");
	}

	@Override
	public void render () {
		//bg default color
		ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);

		//logic
		collisionTest();

		//rendering

		bg.renderBackground();
		player.renderPlayer();
		bulletPool.renderBulletPool(player.getSpritePositionX(), player.getSpritePositionY(), 
		player.getSpriteSizeX(), player.getSpriteSizeY(), player.rotateToCursor() - 90, em); //-op because bullets are in a diferent orientation
		e.renderEnemy();
		miniBoss.renderBoss(player.getSpritePositionX(), player.getSpritePositionY());
	}
	
	@Override
	public void dispose () {
		collisionTest();

		bg.disposeBackground();
		cursor.dispose();
		player.disposePlayer();
		bulletPool.disposeBulletPool();
		e.disposeEnemy();
		miniBoss.disposeBoss();
	}

	public void collisionTest(){
		bulletPool.checkBossCollision(miniBoss);
	}
}
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
import com.mygdx.game.uirelated.HealthBar;

public class MyGdxGame extends ApplicationAdapter {
	Player player = new Player();
	BulletPool bulletPool = new BulletPool(50);
	CustomCursor cursor = new CustomCursor();
	Enemy e;
	Background bg = new Background();
	EventManager em = new EventManager();
	BossFundamentals miniBoss;

	HealthBar healthBar = new HealthBar();

	
	@Override
	public void create () {
		bg.createBackground(Gdx.graphics.getWidth()*1.96f, Gdx.graphics.getHeight(), "map1Slow.png", "map1Fast.png");
		cursor.create();
		e = new Enemy(0,0,300,300,100,
		"EnemyBullet.png", "EnemyBullet", "PlayerTransformation.png");
		bulletPool.createBulletPool("PlayerBullet.png", "SimpleBullet");
		player.createPlayer();
		em.addShotListener(player);
		miniBoss = new BossFundamentals(500, 500, 200,200,10000,"EnemyBullet.png", "EnemyBullet", "Satellite.png");
		healthBar.createHealthBar();
	}

	@Override
	public void render () {
		//bg default color
		ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);

		//logic
		collisionTest();

		//rendering

		bg.renderBackground();
		miniBoss.render(player.getCenterX(), player.getCenterY());
		e.render(player.getCenterX(), player.getCenterY());
		player.renderPlayer();
		bulletPool.renderBulletPoolPlayer(player.getSpritePositionX(), player.getSpritePositionY(),
		player.getSpriteSizeX(), player.getSpriteSizeY(), player.rotateToCursor() - 90, em); //-op because bullets are in a diferent orientation
		healthBar.renderHealthBar(player);
	}
	
	@Override
	public void dispose () {
		collisionTest();

		bg.disposeBackground();
		cursor.dispose();
		player.disposePlayer();
		bulletPool.disposeBulletPool();
		e.dispose();
		miniBoss.dispose();
		healthBar.disposeHealthBar();
	}

	public void collisionTest(){

		bulletPool.checkBossCollision(miniBoss);
		miniBoss.checkPlayerCollision(player);

	}
}
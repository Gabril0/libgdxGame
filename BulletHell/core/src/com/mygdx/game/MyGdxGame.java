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

import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {
	Player player = new Player();
	CustomCursor cursor = new CustomCursor();
	Enemy e;
	Background bg = new Background();
	BossFundamentals miniBoss;

	HealthBar healthBar = new HealthBar();

	ArrayList <Enemy> enemiesList = new <Enemy>ArrayList();

	
	@Override
	public void create () {
		bg.createBackground(Gdx.graphics.getWidth()*1.96f, Gdx.graphics.getHeight(), "map1Slow.png", "map1Fast.png");
		cursor.create();
		e = new Enemy(400,400,300,300,100,
		"EnemyBullet.png", "EnemyBullet", "PlayerTransformation.png");
		player.createPlayer();
		miniBoss = new BossFundamentals(500, 500, 200,200,10000,"EnemyBullet.png", "EnemyBullet", "Satellite.png");
		healthBar.createHealthBar();
		enemiesList.add(e);
		enemiesList.add(miniBoss);
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
		healthBar.renderHealthBar(player);
	}
	
	@Override
	public void dispose () {
		collisionTest();

		bg.disposeBackground();
		cursor.dispose();
		player.disposePlayer();
		e.dispose();
		miniBoss.dispose();
		healthBar.disposeHealthBar();
	}

	public void collisionTest(){
		for (Enemy enemy:enemiesList) {
			player.checkCollision(enemy);
			enemy.checkCollision(player);
		}

	}
}
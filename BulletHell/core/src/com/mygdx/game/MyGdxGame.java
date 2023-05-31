package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.backgroundAndCursor.Background;
import com.mygdx.game.backgroundAndCursor.CustomCursor;
import com.mygdx.game.bosses.BossFundamentals;
import com.mygdx.game.bosses.Satellite;
import com.mygdx.game.enemy.Enemy;
import com.mygdx.game.player.Player;


import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {
	Player player = new Player();
	CustomCursor cursor = new CustomCursor();
	Enemy e;
	Background bg = new Background();
	BossFundamentals miniBoss;



	ArrayList<Enemy> enemiesList = new ArrayList<>();

	
	@Override
	public void create () {
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		bg.createBackground(width*1.96f, height, "map1Slow.png", "map1Fast.png");
		cursor.create();
		e = new Enemy(width/10,height/10,300,300,1000,
		"EnemyBullet.png", "EnemyBullet", "star.png");
		player.createPlayer();
		miniBoss = new Satellite(width/8, height/8, 200,200,10000,"EnemyBullet.png", "EnemyBullet", "Satellite.png");

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

	}
	
	@Override
	public void dispose () {
		collisionTest();

		bg.disposeBackground();
		cursor.dispose();
		player.disposePlayer();
		e.dispose();
		miniBoss.dispose();

	}

	public void collisionTest(){
		for (Enemy enemy:enemiesList) {
			player.checkCollision(enemy);
			enemy.checkCollision(player);
		}

	}
}
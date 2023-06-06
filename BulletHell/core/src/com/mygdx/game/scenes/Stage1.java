package com.mygdx.game.scenes;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.backgroundAndCursor.Background;
import com.mygdx.game.bosses.BossFundamentals;
import com.mygdx.game.bosses.Satellite;
import com.mygdx.game.enemy.Enemy;
import com.mygdx.game.player.Player;

public class Stage1 extends StageFundamental {
	public Stage1(float width, float height) {
		super(width, height);
	}

	Player player = new Player();

	Background bg = new Background();

	Enemy e;
	BossFundamentals miniBoss;
	ArrayList<Enemy> enemiesList = new ArrayList<>();

	public void create() {
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();

		bg.createBackground(width * 1.96f, height, "map1Slow.png", "map1Fast.png");
		e = new Enemy(width / 10, height / 10, 300, 300, 1000,
				"EnemyBullet.png", "EnemyBullet", "star.png");
		player.createPlayer();
		miniBoss = new Satellite(width / 8, height / 8, 200, 200, 10000, "EnemyBullet.png", "EnemyBullet",
				"Satellite.png");

		enemiesList.add(e);
		enemiesList.add(miniBoss);
	}

	public void render() {
		if (isPaused) {
			float mouseX = Gdx.input.getX();
			float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
	
			if (mouseY >= Gdx.graphics.getHeight() * 0.66 && Gdx.input.isButtonJustPressed(Buttons.LEFT) 
			) {
				System.out.println("Clicked resume");
				isPaused = false;
			}
			// Handle other pause-related actions here
		} else {
			// bg default color
			ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);
	
			// logic
			collisionTest();
	
			// rendering
			bg.renderBackground();
			miniBoss.render(player.getCenterX(), player.getCenterY());
			e.render(player.getCenterX(), player.getCenterY());
			player.renderPlayer();
		}
		pause();
	}
	

	public void dispose() {
		bg.disposeBackground();
		player.disposePlayer();
		e.dispose();
		miniBoss.dispose();
	}

	public void collisionTest() {
		for (Enemy enemy : enemiesList) {
			player.checkCollision(enemy);
			enemy.checkCollision(player);
		}

	}
}
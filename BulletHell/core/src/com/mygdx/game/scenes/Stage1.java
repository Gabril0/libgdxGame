package com.mygdx.game.scenes;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.backgroundAndCursor.Background;
import com.mygdx.game.bosses.BossFundamentals;
import com.mygdx.game.bosses.Satellite;
import com.mygdx.game.bosses.SpaceMan;
import com.mygdx.game.enemy.Enemy;
import com.mygdx.game.player.Player;

public class Stage1 extends StageFundamental {
	public Stage1(float width, float height) {
		super(width, height);
	}

	private Background bg = new Background();

	private Enemy e;
	private BossFundamentals satellite;
	private BossFundamentals spaceMan;
	private ArrayList<Enemy> enemiesList = new ArrayList<>();




	public void create() {
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		bg.createBackground(width * 1.96f, height, "Backgrounds/map2Slow.png", "Backgrounds/map2Fast.png", "Backgrounds/opacityEffect2.png");
		e = new Enemy(width / 10, height / 10, 300, 300, 1000,
				"Bullets/StarBullet.png", "EnemyBullet", "Enemies/star.png");

		player.createPlayer();
		satellite = new Satellite(width / 8, height / 8, 200, 200, 10000, "Bullets/EnemyBullet.png", "EnemyBullet",
				"Enemies/Satellite.png");
		spaceMan = new SpaceMan(width / 2, height / 2, 300, 300, 8000, "Bullets/EnergyBullet.png", "EnemyBullet",
				"Enemies/SpaceMan.png");

		enemiesList.add(e);
		enemiesList.add(satellite);
		enemiesList.add(spaceMan);
	}

	public void renderContinuation() {

			// bg default color
			ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);
	
			// logic
			collisionTest();
	
			// rendering
			bg.renderBackground();
			satellite.render(player.getCenterX(), player.getCenterY());
			spaceMan.render(player.getCenterX(), player.getCenterY());
			e.render(player.getCenterX(), player.getCenterY());
			player.renderPlayer();

			checkBossDefeat(satellite);
			checkBossDefeat(spaceMan);

	}
	

	public void dispose() {
		bg.disposeBackground();
		player.disposePlayer();
		e.dispose();
		satellite.dispose();
		spaceMan.dispose();
	}

	public void collisionTest() {
		for (Enemy enemy : enemiesList) {
			player.checkCollision(enemy);
			enemy.checkCollision(player);
		}

	}
}

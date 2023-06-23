package com.mygdx.game.scenes;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.backgroundAndCursor.Background;
import com.mygdx.game.bosses.*;
import com.mygdx.game.bosses.BossFundamentals;
import com.mygdx.game.bosses.Satellite;
import com.mygdx.game.enemy.Bull;
import com.mygdx.game.enemy.Enemy;
import com.mygdx.game.enemy.Factory;
import com.mygdx.game.enemy.Fire;
import com.mygdx.game.enemy.GiantFace;
import com.mygdx.game.enemy.Mirror;
import com.mygdx.game.enemy.Shield;
import com.mygdx.game.enemy.StaticShootState;
import com.mygdx.game.enemy.VariableShootState;
import com.mygdx.game.player.Player;

public class Stage1 extends StageFundamental {
	public Stage1(float width, float height) {
		super(width, height);
	}

	private Background bg = new Background();

	private Enemy e;
	private Satellite satellite;
	private SpaceMan spaceMan;
	private BlackHole blackHole;
	private Alien alien;
	private Sun sun;
	private BullBoss bullBoss;
	private ArrayList<Enemy> enemiesList = new ArrayList<>();



	BossFundamentals miniBoss;
	Bull bullEnemy;
	Fire fireEnemy;
	Fire staticShootFire;
	Fire variableShootFire;
	GiantFace giantFaceEnemy;
	Factory factoryEnemy;
	Shield shieldEnemy;
	Mirror mirrorEnemy;

	public void create() {
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		bg.createBackground(width * 1.96f, height, "Backgrounds/map2Slow.png", "Backgrounds/map2Fast.png", "Backgrounds/opacityEffect2.png");
		e = new Enemy(width / 10, height / 10, 300, 300, 1000,
				"Bullets/StarBullet.png", "EnemyBullet", "Enemies/star.png");

		player.createPlayer();
		satellite = new Satellite(width / 8, height / 8, 200, 200, 10000, "Bullets/EnemyBullet.png", "EnemyBullet",
				"Enemies/Satellite.png");
		spaceMan = new SpaceMan(width / 2, height / 2, 300, 300, 8000, "Bullets/EnergyBullet.png", "EnergyBullet",
				"Enemies/SpaceMan.png");
		blackHole = new BlackHole(width / 2, height / 2, 300, 300, 10000, "Bullets/StarBullet.png", "EnemyBullet",
				"Enemies/BlackHole.png");
		alien = new Alien(width / 2, height / 2, 50, 50, 3000, "Bullets/EnergyBullet.png", "EnergyBullet",
				"Enemies/Alien.png");
		sun = new Sun(width, height, 0, 0, 30000, "Bullets/SunBullet.png", "SlowBullet",
				"Enemies/Sun.png");
		bullBoss = new BullBoss(width / 2, height / 2, 50, 50, 10000, "Bullets/EnemyBullet.png", "EnergyBullet",
				"Enemies/BullBoss.png");


		//enemiesList.add(e);
		//enemiesList.add(satellite);
		//enemiesList.add(spaceMan);
		//enemiesList.add(blackHole);
		//enemiesList.add(alien);
		//enemiesList.add(sun);
		enemiesList.add(bullBoss);
		// bullEnemy = new Bull(0, height - 1, 700, 700, 1000, 
		// "EnemyBullet.png", "EnemyBullet", "bull.png");
		// fireEnemy = new Fire(0, height/2, 0, 0, 1000,
		// "EnemyBullet.png", "EnemyBullet", "fire.png");
		// staticShootFire = new Fire(0, height/2, 0, 0, 1000,
		// "EnemyBullet.png", "EnemyBullet", "fire.png");
		// staticShootFire.changeState(new StaticShootState(staticShootFire));
		// variableShootFire = new Fire(0, height/2, 0, 0, 1000,
		// "EnemyBullet.png", "EnemyBullet", "fire.png");
		// variableShootFire.changeState(new VariableShootState(variableShootFire));
		// giantFaceEnemy = new GiantFace(0, height/2, 100, 100, 1000,
		// "EnemyBullet.png", "EnemyBullet", "giantFace.png");
		// factoryEnemy = new Factory(0, height/2, 0, 0, 1000,
		// "EnemyBullet.png", "EnemyBullet", "factory0.png", "Enemy");
		// shieldEnemy = new Shield(100, 100, 0, 0, 2000, 
		// "EnemyBullet.png", "EnemyBullet", "shield.png");
		mirrorEnemy = new Mirror(0, height/2, 100, 100, 1000,
		"EnemyBullet.png", "EnemyBullet", "mirror.png");


		enemiesList.add(e);
		enemiesList.add(miniBoss);
		// enemiesList.add(bullEnemy);
		// enemiesList.add(fireEnemy);
		// enemiesList.add(staticShootFire);
		// enemiesList.add(variableShootFire);
		// enemiesList.add(giantFaceEnemy);
		// enemiesList.add(factoryEnemy);
		// enemiesList.add(shieldEnemy);
		enemiesList.add(mirrorEnemy);
		// factoryEnemy.addEnemiesCollision(enemiesList);
	}

	public void renderContinuation() {

			// bg default color
			ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);
	
			// logic
			collisionTest();
	
			// rendering
			bg.renderBackground();
			//satellite.render(player.getCenterX(), player.getCenterY());
			//spaceMan.render(player.getCenterX(), player.getCenterY());
			//blackHole.render(player);
			//e.render(player.getCenterX(), player.getCenterY());
			//alien.render(player.getCenterX(), player.getCenterY());
			//sun.render(player.getCenterX(), player.getCenterY());
			bullBoss.render(player.getCenterX(), player.getCenterY());
			miniBoss.render(player.getCenterX(), player.getCenterY());
			// e.render(player.getCenterX(), player.getCenterY());
			// // the bull has to get the actual player position, because it runs right towards him and using the
			// // libgdx natural position system makes it easier
			// bullEnemy.render(player.getSpritePositionX(), player.getSpritePositionY());
			// fireEnemy.render(player.getCenterX(), player.getCenterY());
			// staticShootFire.render(player.getCenterX(), player.getCenterY());
			// variableShootFire.render(player.getCenterX(), player.getCenterY());
			// giantFaceEnemy.render(player.getCenterX(), player.getCenterY());
			// factoryEnemy.render(player.getCenterX(), player.getCenterY());
			// shieldEnemy.render(player.getCenterX(), player.getCenterY());
			mirrorEnemy.render(player.getCenterX(), player.getCenterY());
			player.renderPlayer();

			checkBossDefeat(satellite);
			checkBossDefeat(spaceMan);
			checkBossDefeat(blackHole);
			checkBossDefeat(alien);
			checkBossDefeat(sun);
			checkBossDefeat(bullBoss);


	}
	

	public void dispose() {
		bg.disposeBackground();
		player.disposePlayer();
		e.dispose();
		satellite.dispose();
		spaceMan.dispose();
		blackHole.dispose();
		alien.dispose();
		sun.dispose();
		bullBoss.dispose();
		miniBoss.dispose();
		bullEnemy.dispose();
		fireEnemy.dispose();
		staticShootFire.dispose();
		variableShootFire.dispose();
		giantFaceEnemy.dispose();
		factoryEnemy.dispose();
		shieldEnemy.dispose();
		mirrorEnemy.dispose();
	}

	public void collisionTest() {
		for (Enemy enemy : enemiesList) {
			// if (shieldEnemy.isAlive()){
			// 	player.checkCollision(shieldEnemy);
			// }
			// else {
				player.checkCollision(enemy);
			//}
			enemy.checkCollision(player);
		}
	}
}

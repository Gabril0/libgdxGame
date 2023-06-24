package com.mygdx.game.scenes;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.backgroundAndCursor.Background;
import com.mygdx.game.bosses.*;
import com.mygdx.game.bosses.BossFundamentals;
import com.mygdx.game.bosses.Satellite;
import com.mygdx.game.constants.EnemyConstants;
import com.mygdx.game.constants.SpriteConstants;
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
	private WavesStage1 ws1 = new WavesStage1();
	public Stage1(float width, float height) {
		super(width, height);
	}

	private Background bg = new Background();

//	private Enemy e;
//	private Satellite satellite;
//	private SpaceMan spaceMan;
//	private BlackHole blackHole;
//	private Alien alien;
//	private Sun sun;
//	private BullBoss bullBoss;
//	private ArrayList<Enemy> enemiesList = new ArrayList<>();



//	BossFundamentals miniBoss;
//	Bull bullEnemy;
//	Fire fireEnemy;
//	Fire staticShootFire;
//	Fire variableShootFire;
//	GiantFace giantFaceEnemy;
//	Factory factoryEnemy;
//	Shield shieldEnemy;
//	Mirror mirrorEnemy;

	public void create() {
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();

		player.createPlayer();

		ws1.createWaves(width,height,player);

		bg.createBackground(width * 1.96f, height, "Backgrounds/map1Slow.png", "Backgrounds/map1Fast.png", "Backgrounds/opacityEffect.png");
//		e = new Enemy(width / 10, height / 10, EnemyConstants.ENEMY_SPEED, EnemyConstants.ENEMY_SPEED, EnemyConstants.HEALTH,
//				SpriteConstants.STAR_BULLET, "EnemyBullet", SpriteConstants.ENEMY);
//
//		satellite = new Satellite(width / 8, height / 8, 200, 200, 10000, SpriteConstants.ENEMY_BULLET, "EnemyBullet",
//				SpriteConstants.SATELLITE);
//		spaceMan = new SpaceMan(width / 2, height / 2, 300, 300, 8000, SpriteConstants.ENERGY_BULLET, "EnergyBullet",
//				SpriteConstants.SPACEMAN);
//		blackHole = new BlackHole(width / 2, height / 2, 300, 300, 10000, SpriteConstants.STAR_BULLET, "EnemyBullet",
//				SpriteConstants.BLACKHOLE);
//		alien = new Alien(width / 2, height / 2, 50, 50, 3000, SpriteConstants.ENERGY_BULLET, "EnergyBullet",
//				SpriteConstants.ALIEN);
//		sun = new Sun(width, height, 0, 0, 30000, SpriteConstants.SUN_BULLET, "SlowBullet",
//				SpriteConstants.SUN);
//		bullBoss = new BullBoss(width / 2, height / 2, 50, 50, 10000, SpriteConstants.ENEMY_BULLET, "EnergyBullet",
//				SpriteConstants.BULL_BOSS);
//
//
//		enemiesList.add(satellite);
//		enemiesList.add(spaceMan);
//		enemiesList.add(blackHole);
//		enemiesList.add(alien);
//		enemiesList.add(sun);
//		enemiesList.add(bullBoss);
//		bullEnemy = new Bull(0, height - 1, 700, 700, 1000,
//		SpriteConstants.ENEMY_BULLET, "EnemyBullet", SpriteConstants.BULL, player);
//		fireEnemy = new Fire(0, height/2, 0, 0, 1000,
//		SpriteConstants.ENEMY_BULLET, "EnemyBullet", SpriteConstants.FIRE);
//		staticShootFire = new Fire(0, height/2, 0, 0, 1000,
//		SpriteConstants.ENEMY_BULLET, "EnemyBullet", SpriteConstants.FIRE);
//		staticShootFire.changeState(new StaticShootState(staticShootFire));
//		variableShootFire = new Fire(0, height/2, 0, 0, 1000,
//		"Bullets/EnemyBullet.png", "EnemyBullet", SpriteConstants.FIRE);
//		variableShootFire.changeState(new VariableShootState(variableShootFire));
//		giantFaceEnemy = new GiantFace(0, height/2, 100, 100, 1000,
//		SpriteConstants.ENERGY_BULLET, "EnemyBullet", SpriteConstants.GIANT_FACE);
//		factoryEnemy = new Factory(0, height/2, 0, 0, 1000,
//		"Bullets/EnemyBullet.png", "EnemyBullet", SpriteConstants.FACTORY, "Bull", player);
//		shieldEnemy = new Shield(100, 100, 0, 0, 2000,
//		"Bullets/EnemyBullet.png", "EnemyBullet", SpriteConstants.SHIELD);
//		mirrorEnemy = new Mirror(0, height/2, 100, 100, 1000,
//		"Bullets/EnemyBullet.png", "EnemyBullet", SpriteConstants.MIRROR);
//
//
//		enemiesList.add(e);
//		enemiesList.add(bullEnemy);
//		enemiesList.add(fireEnemy);
//		enemiesList.add(staticShootFire);
//		enemiesList.add(variableShootFire);
//		enemiesList.add(giantFaceEnemy);
//		enemiesList.add(factoryEnemy);
//		enemiesList.add(shieldEnemy);
//		enemiesList.add(mirrorEnemy);
//		factoryEnemy.addEnemiesCollision(enemiesList);
	}

	public void renderContinuation() {

			// bg default color
			ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);
	
			// logic
			ws1.collisionTest();
	
			// rendering
			bg.renderBackground();

			ws1.renderWaves(player.getCenterX(),player.getCenterY());

//			satellite.render(player.getCenterX(), player.getCenterY());
//			spaceMan.render(player.getCenterX(), player.getCenterY());
//			blackHole.render(player);
//			alien.render(player.getCenterX(), player.getCenterY());
//			sun.render(player.getCenterX(), player.getCenterY());
//			bullBoss.render(player.getCenterX(), player.getCenterY());
//			e.render(player.getCenterX(), player.getCenterY());
//			// the bull has to get the actual player position, because it runs right towards him and using the
//			// libgdx natural position system makes it easier
//			bullEnemy.render(player.getSpritePositionX(), player.getSpritePositionY());
//			fireEnemy.render(player.getCenterX(), player.getCenterY());
//			staticShootFire.render(player.getCenterX(), player.getCenterY());
//			variableShootFire.render(player.getCenterX(), player.getCenterY());
//			giantFaceEnemy.render(player.getCenterX(), player.getCenterY());
//			factoryEnemy.render(player.getCenterX(), player.getCenterY());
//			shieldEnemy.render(player.getCenterX(), player.getCenterY());
//			mirrorEnemy.render(player.getCenterX(), player.getCenterY());
			player.renderPlayer();

//			checkBossDefeat(satellite);
//			checkBossDefeat(spaceMan);
//			checkBossDefeat(blackHole);
//			checkBossDefeat(alien);
//			checkBossDefeat(sun);
//			checkBossDefeat(bullBoss);


	}
	

	public void dispose() {
		bg.disposeBackground();
		player.disposePlayer();
		ws1.disposeWaves();
//		e.dispose();
//		satellite.dispose();
//		spaceMan.dispose();
//		blackHole.dispose();

//		alien.dispose();
//		sun.dispose();
//		bullBoss.dispose();
//		bullEnemy.dispose();
//		fireEnemy.dispose();
//		staticShootFire.dispose();
//		variableShootFire.dispose();
//		giantFaceEnemy.dispose();
//		factoryEnemy.dispose();
//		shieldEnemy.dispose();
//		mirrorEnemy.dispose();
	}
}

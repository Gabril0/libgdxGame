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
	private boolean isOver = false;

	public void create(Player player) {
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		ws1.createWaves(width,height,player);

		this.player = player;

		bg.createBackground(width * 1.96f, height, "Backgrounds/map1Slow.png", "Backgrounds/map1Fast.png", "Backgrounds/opacityEffect.png");
	}

	public void renderContinuation() {

			// bg default color
			ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);
	
			// logic
			ws1.collisionTest();
	
			// rendering
			bg.renderBackground();


			ws1.renderWaves(player.getCenterX(),player.getCenterY());

			checkBossDefeat(ws1.checkEvolution());
			player.renderPlayer();
			if(ws1.isOver()){
				setIsOver(true);
			}


	}
	

	public void dispose() {
		bg.disposeBackground();
		ws1.dispose();

	}




}

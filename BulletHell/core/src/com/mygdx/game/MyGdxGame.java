package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.backgroundAndCursor.CustomCursor;
import com.mygdx.game.connection.SQLconnection;
import com.mygdx.game.constants.SpriteConstants;
import com.mygdx.game.player.Player;
import com.mygdx.game.scenes.Stage1;
import com.mygdx.game.scenes.Stage2;
import com.mygdx.game.scenes.Stage3;

public class MyGdxGame extends ApplicationAdapter {
	int currentStage = 1;
	CustomCursor cursor = new CustomCursor();
	Stage1 s1;
	Stage2 s2;
	Stage3 s3;
	Player player;

	Texture congratulations;
	SpriteBatch batch;

	SQLconnection sql = new SQLconnection();
	boolean sqlLock = true;

	float deltaTime, currentTime = 0;
	
	@Override
	public void create () {
		cursor.create();
		player = new Player();
		player.createPlayer();

		s1 = new Stage1(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
		s1.create(player);

		s2 = new Stage2(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
		s2.create(player);

		s3 = new Stage3(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
		s3.create(player);

		congratulations = new Texture("UI/Finish.png");
		batch = new SpriteBatch();





	}

	@Override
	public void render () {
		deltaTime = Gdx.graphics.getDeltaTime();
		currentTime += deltaTime;
		if(!player.isAlive()){
			if(sqlLock) {
				sql.addDeath(currentStage,currentTime);
				sqlLock = false;
			}
		}
		//currentStage = 3;
		if(currentStage == 1) {
			s1.render();
		}
		if(s1.isOver()) {
			currentStage = 2;
			s1.setIsOver(false);
			s1.dispose();
			s2.create();

		}
		if(currentStage == 2){
			s2.render();
		}
		if(s2.isOver()) {
			currentStage = 3;
			s2.setIsOver(false);
			s2.dispose();
			s3.create();
		}
		if(currentStage == 3){
			s3.render();
		}
		if(s3.isOver()) {

			s3.setIsOver(false);
			s3.dispose();
			if(sqlLock) {
				sql.addDeath(4,currentTime);
				sqlLock = false;
			}
			batch.begin();
			batch.draw(congratulations,0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			batch.end();

		}

	}
	
	@Override
	public void dispose () {
		cursor.dispose();
		player.disposePlayer();
		s1.dispose();
		s2.dispose();
		s3.dispose();
	}
}
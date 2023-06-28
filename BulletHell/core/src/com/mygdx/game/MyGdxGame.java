package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.backgroundAndCursor.CustomCursor;
import com.mygdx.game.connection.SQLconnection;
import com.mygdx.game.constants.SpriteConstants;
import com.mygdx.game.player.Player;
import com.mygdx.game.scenes.Stage1;
import com.mygdx.game.scenes.Stage2;
import com.mygdx.game.scenes.Stage3;
import com.mygdx.game.scenes.StageFundamental;

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

	float deltaTime, currentTime = 0, finalTime, lowerTimeEver;

	BitmapFont font;

	private Music endingSong;
	private StageFundamental stageObject;
	
	@Override
	public void create () {
		cursor.create();
		player = new Player();
		player.createPlayer();

		s1 = new Stage1(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
		s1.create(player);

		s2 = new Stage2(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());

		s3 = new Stage3(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());

		congratulations = new Texture("UI/Finish.png");
		batch = new SpriteBatch();

		font = new BitmapFont(Gdx.files.internal("Fonts/BoldBasic.fnt"));
		font.setColor(Color.WHITE);
		font.getData().setScale(2f);

		stageObject = s1;


	}

	@Override
	public void render () {
		deltaTime = Gdx.graphics.getDeltaTime();

		if(!player.isAlive()){
			if(sqlLock) {
				sql.addDeath(currentStage,currentTime);
				sqlLock = false;
				stageObject.stopSong();
				endingSong = Gdx.audio.newMusic(Gdx.files.internal("Sounds/Major Tom.mp3"));
				endingSong.play();
				endingSong.setLooping(true);
			}
			restart();
		}
		if(currentStage == 1) {
			s1.render();
		}
		if(s1.isOver()) {
			currentStage = 2;
			s1.setIsOver(false);
			s1.dispose();
			s2.create(player);
			s2.create();
			stageObject = s2;
		}
		if(currentStage == 2){
			s2.render();
		}
		if(s2.isOver()) {
			currentStage = 3;
			s2.setIsOver(false);
			s2.dispose();
			s3.create(player);
			s3.create();
			stageObject = s3;
		}
		if(currentStage == 3){
			s3.render();
		}
		if(s3.isOver()) {

			s3.setIsOver(false);
			s3.dispose();

			if(sqlLock) {
				finalTime = currentTime;
				sql.addDeath(4,currentTime);
				lowerTimeEver = sql.getLowerTime();
				font.getData().setScale(3f);
				sqlLock = false;

				stageObject.stopSong();
				endingSong = Gdx.audio.newMusic(Gdx.files.internal("Sounds/Final-Starman.mp3"));
				endingSong.play();
				endingSong.setLooping(true);
			}
			batch.begin();
			batch.draw(congratulations,0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			font.draw(batch, "Your Time: " + finalTime, 0, Gdx.graphics.getHeight() * 0.2f);
			font.draw(batch, "World Record: " + lowerTimeEver, 0, Gdx.graphics.getHeight() * 0.3f);

			batch.end();

			restart();

		}
		if(!s1.isPaused() && !s2.isPaused() && !s3.isPaused() && sqlLock) {
			currentTime += deltaTime;
			batch.begin();

			font.draw(batch, "Time: " + currentTime, 10, Gdx.graphics.getHeight() - 10);

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

	public void restart(){
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
				try {
					dispose();
				} catch (Exception e) {
					
				}
				endingSong.stop();
				sqlLock = true;
				currentStage = 1;
				currentTime = 0;
				create();
			}
	}
}
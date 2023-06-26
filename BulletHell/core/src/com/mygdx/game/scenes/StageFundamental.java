package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.bosses.BossFundamentals;
import com.mygdx.game.evolution.*;
import com.mygdx.game.listeners.EventManager;
import com.mygdx.game.listeners.Listener;
import com.mygdx.game.player.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class StageFundamental implements Stage {
	protected Player player = new Player();;
	protected boolean isPaused = false;
	protected boolean isEvoluting = false;
	private Texture pauseTexture;
	private SpriteBatch batch = new SpriteBatch();
	private float width, height;
	protected Sound sound;
	protected ArrayList<Evolution> evolutions = new ArrayList<Evolution>();
	protected Random random = new Random();
	protected boolean isBossDefeated = false;
	private boolean evolutionPoolingLock = true;
	private int number[] = new int[4];
	private Texture textures[] = new Texture[4];
	private int eNumber[] = new int[4];
	protected boolean upgradeLock = false;

	StageFundamental(float width, float height) {
		this.width = width;
		this.height = height;
		pauseTexture = new Texture("UI/pause.png");
		batch = new SpriteBatch();
		isPaused = false;
		sound = Gdx.audio.newSound(Gdx.files.internal("Sounds/funnySound.mp3"));

		evolutions.add(new StoredEnergy());
		evolutions.add(new Overweight());
		evolutions.add(new SuddenDeath());
		evolutions.add(new CosmicHeal());
		evolutions.add(new CosmicStrength());
		evolutions.add(new CosmicFlow());
		evolutions.add(new BigBang());
		evolutions.add(new GalacticCannon());
	}

	@Override
	public void create() {
	}

	@Override
	public void render() {
		if (isPaused && !isEvoluting) {
			float mouseX = Gdx.input.getX();
			float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

			if (mouseY >= Gdx.graphics.getHeight() * 0.66 && Gdx.input.isButtonJustPressed(Buttons.LEFT)
			) {
				isPaused = false;
			}
		}
		if(isEvoluting ){

		}
		if(!isPaused && !isEvoluting) {
			renderContinuation();
		}
		pause();
	}

	@Override
	public void renderContinuation() {

	}

	@Override
	public void dispose() {
	}

	protected void pause() {
		if ((Gdx.input.isKeyJustPressed(Keys.ESCAPE) || isPaused) && !isEvoluting) {
			float mouseX = Gdx.input.getX();
			float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
			if (((mouseY >= height * 0.33f && mouseY <= height * 0.66) &&
			Gdx.input.isButtonJustPressed(Buttons.LEFT))) {;
				sound.play(1f);
			}
			if (mouseY >= height * 0.66 && Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
				isPaused = false;
			}
			if (mouseY <= height * 0.33f && Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
				System.exit(0);
			} else {
				isPaused = true;
					batch.begin();
					batch.setColor(Color.WHITE);
					batch.draw(pauseTexture, 0, 0, width, height);
					batch.end();

			}
		}
		if(isBossDefeated){
			isEvoluting = true;
			evolute();
		}

	}

	protected void evolute(){

		if(evolutionPoolingLock) {
			for (int i = 1; i <= 3; i++) { //gets random values for the evolution pool
				number[i] = random.nextInt(evolutions.size());
			}

			for (int i = 1; i < 4; i++) { //get the evolution description and get the order
				textures[i] = evolutions.get(number[i]).getDescription();
				eNumber[i] = number[i];
			}
			evolutionPoolingLock = false;
		}
		batch.begin();

		batch.setColor(110 / 255f, 221 / 255f, 255 / 255f, 1); // Divide color values by 255.0
		batch.draw(textures[1], 0, 0, width/3, height);

		batch.setColor(250/ 255f, 232 / 255f, 0, 1);
		batch.draw(textures[2], width*0.335f, 0, width/3, height);

		batch.setColor(250/ 255f, 75 / 255f, 126 / 255f, 1);
		batch.draw(textures[3], width*0.67f, 0, width/3, height);

		batch.end();

		float mouseX = Gdx.input.getX();
		if (((mouseX >= width * 0.33f && mouseX <= width * 0.66) &&
				Gdx.input.isButtonJustPressed(Buttons.LEFT))) {
			evolutions.get(eNumber[2]).makeChanges(player); //selects then removes theone selected
			evolutions.remove(eNumber[2]);
			isEvoluting = false;
			isBossDefeated = false;
			evolutionPoolingLock = true;

		}
		if (mouseX >= width * 0.66 && Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
			evolutions.get(eNumber[3]).makeChanges(player);
			evolutions.remove(eNumber[3]);
			isEvoluting = false;
			isBossDefeated = false;
			evolutionPoolingLock = true;

		}
		if (mouseX <= width * 0.33f && Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
			evolutions.get(eNumber[1]).makeChanges(player);
			evolutions.remove(eNumber[1]);
			isEvoluting = false;
			isBossDefeated = false;
			evolutionPoolingLock = true;
		}


	}

	protected void  checkBossDefeat(boolean bool){
		if(bool) {
			if (!upgradeLock) {
				isBossDefeated = true;
				upgradeLock = true;
			}
		}

	}


	public void setIsBossDefeated(boolean bool) {
		isBossDefeated = bool;
	}
}

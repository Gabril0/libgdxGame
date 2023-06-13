package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StageFundamental implements Stage {
	public boolean isPaused = false;
	private Texture pauseTexture;
	private SpriteBatch batch = new SpriteBatch();
	private float width, height;
	protected Sound sound;

	StageFundamental(float width, float height) {
		this.width = width;
		this.height = height;
		pauseTexture = new Texture("pause.png");
		batch = new SpriteBatch();
		isPaused = false;
		sound = Gdx.audio.newSound(Gdx.files.internal("Sounds/funnySound.mp3"));
	}

	@Override
	public void create() {
	}

	@Override
	public void render() {
	}

	@Override
	public void dispose() {
	}

	protected void pause() {
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE) || isPaused) {
			float mouseX = Gdx.input.getX();
			float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
			if (((mouseY >= height * 0.33f && mouseY <= height * 0.66) &&
			Gdx.input.isButtonJustPressed(Buttons.LEFT))) {;
				System.out.println("clicked middle");
				sound.play(1f);
			}
			if (mouseY >= height * 0.66 && Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
				System.out.println("clicked up");
				isPaused = false;
			}
			if (mouseY <= height * 0.33f && Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
				System.exit(0);
				System.out.println("ir para o menu");
			} else {
				isPaused = true;
					batch.begin();
					batch.draw(pauseTexture, 0, 0, width, height);
					batch.end();

			}
		}

	}
}

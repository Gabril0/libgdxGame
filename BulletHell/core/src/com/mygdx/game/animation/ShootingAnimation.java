package com.mygdx.game.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class ShootingAnimation implements Animation{
    private Array<Texture> shootAnimation;
    private int currentFrameIndex;
    private float frameDuration = 0.1f; //duration of animation speed
    private float frameTimer = 0f;
    @Override
    public void create() {
        //loading animation "shoot"
        shootAnimation = new Array<Texture>();
        shootAnimation.add(new Texture("animationShoot2outOf3.png"));
        shootAnimation.add(new Texture("animationShoot3outOf3.png"));
        shootAnimation.add(new Texture("PlayerBaseSprite.png"));
    }

    @Override
    public void render(float spritePositionX, float spritePositionY, float spriteSizeX, float spriteSizeY, float rotateToCursor, SpriteBatch batch) {
        frameTimer += Gdx.graphics.getDeltaTime(); //getting the time from the beginning of animation
        if (frameTimer >= frameDuration) {
            frameTimer = 0f;
            currentFrameIndex = (currentFrameIndex + 1) % shootAnimation.size; //calls next frame
        }
        Texture currentFrame = shootAnimation.get(currentFrameIndex);//draw the current frame
        batch.draw(currentFrame, spritePositionX, spritePositionY, spriteSizeX / 2, spriteSizeY / 2, spriteSizeX,
                spriteSizeY, 1f, 1f, rotateToCursor, 0, 0, currentFrame.getWidth(),
                currentFrame.getHeight(), false, false);
    }

    @Override
    public void dispose() {
        for (Texture texture: shootAnimation) {
            texture.dispose();
        }

    }
}

package com.mygdx.game.animation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class AnimationFundamentals implements Animation{
    protected Array<Texture> animation;
    protected int currentFrameIndex;
    protected float frameDuration = 0.1f; //duration of animation speed
    protected float frameTimer = 0f;
    private boolean animationWasFinished = false;
    private boolean animationEndLock = false;
    @Override
    public void create() {
        animation = new Array<Texture>();
    }

    @Override
    public void render(float spritePositionX, float spritePositionY, float spriteSizeX, float spriteSizeY, float rotateToCursor, SpriteBatch batch) {
        frameTimer += Gdx.graphics.getDeltaTime(); //getting the time from the beginning of animation
        if (frameTimer >= frameDuration) {
            frameTimer = 0f;
            currentFrameIndex = (currentFrameIndex + 1) % animation.size; //calls next frame
        }

        Texture currentFrame = animation.get(currentFrameIndex); //draw the current frame
        batch.draw(currentFrame, spritePositionX, spritePositionY, spriteSizeX / 2, spriteSizeY / 2, spriteSizeX,
                spriteSizeY, 1f, 1f, rotateToCursor, 0, 0, currentFrame.getWidth(),
                currentFrame.getHeight(), false, false);

        if (currentFrameIndex == animation.size - 1 && !animationEndLock) {
            animationEndLock = true;
            animationWasFinished = true;
        }
    }


    @Override
    public void dispose() {
        for (Texture texture: animation) {
            texture.dispose();
        }

    }
    public void setFrameDuration(float frameDuration) {
        this.frameDuration = frameDuration;
    }

    public boolean getWasFinished() {
        return animationWasFinished;
    }
}
package com.mygdx.game.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public interface Animation {
    public void create();
    public void render(float spritePositionX, float spritePositionY, float spriteSizeX, float spriteSizeY, float rotateToCursor, SpriteBatch batch);
    public void dispose();
}

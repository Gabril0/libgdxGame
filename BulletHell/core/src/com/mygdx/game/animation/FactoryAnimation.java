package com.mygdx.game.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class FactoryAnimation extends AnimationFundamentals {
    @Override
    public void create() {
        animation = new Array<Texture>();

        setFrameDuration(0.99f);

        animation.add(new Texture("Enemies/factory0.png"));
        animation.add(new Texture("Enemies/factory25.png"));
        animation.add(new Texture("Enemies/factory50.png"));
        animation.add(new Texture("Enemies/factory75.png"));
        animation.add(new Texture("Enemies/factory100.png"));
    }
}

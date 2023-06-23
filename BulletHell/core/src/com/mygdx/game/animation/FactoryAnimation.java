package com.mygdx.game.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class FactoryAnimation extends AnimationFundamentals {
    @Override
    public void create() {
        animation = new Array<Texture>();

        setFrameDuration(0.99f);

        animation.add(new Texture("factory0.png"));
        animation.add(new Texture("factory25.png"));
        animation.add(new Texture("factory50.png"));
        animation.add(new Texture("factory75.png"));
        animation.add(new Texture("factory100.png"));
    }
}

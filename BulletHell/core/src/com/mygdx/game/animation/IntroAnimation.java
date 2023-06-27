package com.mygdx.game.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class IntroAnimation extends AnimationFundamentals{
    @Override
    public void create() {
        animation = new Array<Texture>();
        setFrameDuration(0.15f);

        animation.add(new Texture("UI/Explosion6.png"));
        animation.add(new Texture("UI/Explosion5.png"));
        animation.add(new Texture("UI/Explosion4.png"));
        animation.add(new Texture("UI/Explosion3.png"));
        animation.add(new Texture("UI/Explosion2.png"));
        animation.add(new Texture("UI/Explosion1.png"));


    }
}

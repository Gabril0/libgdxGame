package com.mygdx.game.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class Explosion extends AnimationFundamentals{
    @Override
    public void create() {
        animation = new Array<Texture>();
        setFrameDuration(0.08f);

        animation.add(new Texture("UI/Explosion1.png"));
        animation.add(new Texture("UI/Explosion2.png"));
        animation.add(new Texture("UI/Explosion3.png"));
        animation.add(new Texture("UI/Explosion4.png"));
        animation.add(new Texture("UI/Explosion5.png"));
        animation.add(new Texture("UI/Explosion6.png"));


    }
}

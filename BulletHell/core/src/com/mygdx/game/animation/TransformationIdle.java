package com.mygdx.game.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class TransformationIdle extends AnimationFundamentals{
    @Override
    public void create() {
        animation = new Array<Texture>();
        setFrameDuration(1f);
        animation.add(new Texture("playerAnimation/PlayerTransformation.png"));
        animation.add(new Texture("playerAnimation/TransformationIdle2.png"));

    }
}

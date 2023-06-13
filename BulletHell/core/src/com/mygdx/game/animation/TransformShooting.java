package com.mygdx.game.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class TransformShooting extends AnimationFundamentals{
    @Override
    public void create() {
        animation = new Array<Texture>();
        animation.add(new Texture("playerAnimation/TransformationAttack1.png"));
        animation.add(new Texture("playerAnimation/TransformationAttack2.png"));
        animation.add(new Texture("playerAnimation/PlayerTransformation.png"));
    }
}

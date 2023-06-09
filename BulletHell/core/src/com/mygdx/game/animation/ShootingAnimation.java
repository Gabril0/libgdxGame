package com.mygdx.game.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class ShootingAnimation extends AnimationFundamentals{

    @Override
    public void create() {
        animation = new Array<Texture>();
        animation.add(new Texture("playerAnimation/animationShoot2outOf3.png"));
        animation.add(new Texture("playerAnimation/animationShoot3outOf3.png"));
        animation.add(new Texture("playerAnimation/PlayerBaseSprite.png"));
    }
    

}

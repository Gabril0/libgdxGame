package com.mygdx.game.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class PlayerTransitionAnimation extends AnimationFundamentals{
    @Override
    public void create() {
        animation = new Array<Texture>();
        setFrameDuration(0.075f);

        animation.add(new Texture("playerAnimation/playerTransition1.png"));
        animation.add(new Texture("playerAnimation/playerTransition2.png"));
        animation.add(new Texture("playerAnimation/playerTransition2.png"));
        animation.add(new Texture("playerAnimation/playerTransition2.png"));
        animation.add(new Texture("playerAnimation/playerTransition2.png"));
        animation.add(new Texture("playerAnimation/playerTransition2.png"));
        animation.add(new Texture("playerAnimation/playerTransition2.png"));
        animation.add(new Texture("playerAnimation/playerTransition3.png"));
        animation.add(new Texture("playerAnimation/playerTransition4.png"));
        animation.add(new Texture("playerAnimation/playerTransition4.png"));
        animation.add(new Texture("playerAnimation/playerTransition4.png"));
        animation.add(new Texture("playerAnimation/playerTransition4.png"));
        animation.add(new Texture("playerAnimation/playerTransition4.png"));
        animation.add(new Texture("playerAnimation/playerTransition4.png"));
        animation.add(new Texture("playerAnimation/playerTransition4.png"));
        animation.add(new Texture("playerAnimation/playerTransition5.png"));
        animation.add(new Texture("playerAnimation/playerTransition6.png"));
        animation.add(new Texture("playerAnimation/playerTransition6.png"));
        animation.add(new Texture("playerAnimation/playerTransition7.png"));
        animation.add(new Texture("playerAnimation/playerTransition7.png"));
        animation.add(new Texture("playerAnimation/playerTransition8.png"));
        animation.add(new Texture("playerAnimation/playerTransition9.png"));
        animation.add(new Texture("playerAnimation/playerTransition10.png"));
        animation.add(new Texture("playerAnimation/playerTransition11.png"));
        animation.add(new Texture("playerAnimation/playerTransition12.png"));
        animation.add(new Texture("playerAnimation/playerTransition13.png"));
        animation.add(new Texture("playerAnimation/playerTransition14.png"));
        animation.add(new Texture("playerAnimation/playerTransition14.png"));




    }
}

package com.mygdx.game.animation;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;


public class SatelliteIdle extends AnimationFundamentals{
    @Override
    public void create() {
        animation = new Array<Texture>();
        setFrameDuration(0.5f);

        animation.add(new Texture("SatelliteIdle(2-3).png"));
        animation.add(new Texture("SatelliteIdle(3-3).png"));
        animation.add(new Texture("SatelliteIdle(3-3).png"));
        animation.add(new Texture("SatelliteIdle(3-3).png"));

    }
    
}

package com.mygdx.game.evolution;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.player.Player;

public class BigBang implements Evolution {
    private Texture description = new Texture("UI/BigBang.png");

    @Override
    public Texture getDescription() {
        return description;
    }

    @Override
    public void makeChanges(Player player) {
        player.setShootingRate(0.5f);
        player.setLife(2f);
        player.setSpeed(1.5f); //this is a lie, it isn`t really 2x because it would  be really hard to maneuver
        player.setDamage(2f);

    }
}

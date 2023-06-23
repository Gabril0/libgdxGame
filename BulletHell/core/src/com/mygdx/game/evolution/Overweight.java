package com.mygdx.game.evolution;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.player.Player;

public class Overweight implements Evolution {
    private Texture description = new Texture("UI/Overweight.png");

    @Override
    public Texture getDescription() {
        return description;
    }

    @Override
    public void makeChanges(Player player) {
        player.setDamage(2f);
        player.setSpeed(0.5f);
    }

}

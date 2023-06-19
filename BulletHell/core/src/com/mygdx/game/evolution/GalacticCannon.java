package com.mygdx.game.evolution;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.player.Player;

public class GalacticCannon implements Evolution {
    private Texture description = new Texture("UI/GalacticCannon.png");

    @Override
    public Texture getDescription() {
        return description;
    }

    @Override
    public void makeChanges(Player player) {
        player.setDamage(5f);
        player.setShootingRate(4f);
        player.setCanSlowdown(true);

    }
}

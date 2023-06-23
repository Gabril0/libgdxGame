package com.mygdx.game.evolution;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.player.Player;

public class StoredEnergy implements Evolution {
    private Texture description = new Texture("UI/StoredEnergy.png");

    public StoredEnergy() {
    }

    @Override
    public Texture getDescription() {
        return description;
    }

    @Override
    public void makeChanges(Player player) {
        player.setDamage(0.3f); //damage is now lower
        player.setShootingRate(0.5f); //halves the cooldown
    }

}

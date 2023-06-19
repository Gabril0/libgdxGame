package com.mygdx.game.evolution;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.player.Player;

public class CosmicFlow implements Evolution {
    private Texture description = new Texture("UI/CosmicFlow.png");

    public CosmicFlow() {
    }

    @Override
    public Texture getDescription() {
        return description;
    }

    @Override
    public void makeChanges(Player player) {
        player.setShootingRate(0.5f);

    }
}

package com.mygdx.game.evolution;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.player.Player;

public class CosmicHeal implements Evolution {
    private Texture description = new Texture("UI/CosmicHeal.png");

    @Override
    public Texture getDescription() {
        return description;
    }

    @Override
    public void makeChanges(Player player) {
        player.setLife(2f);
    }
}

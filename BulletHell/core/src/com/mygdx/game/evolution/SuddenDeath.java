package com.mygdx.game.evolution;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.player.Player;

public class SuddenDeath implements Evolution {
    private Texture description = new Texture("UI/SuddenDeath.png");

    @Override
    public Texture getDescription() {
        return description;
    }

    @Override
    public void makeChanges(Player player) {
        player.setDamage(10f);
        player.setLife(0.01f);
    }
}

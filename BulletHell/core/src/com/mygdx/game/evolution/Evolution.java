package com.mygdx.game.evolution;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.player.Player;

public interface Evolution {
    public Texture getDescription(); //gets a image with the description
    public void makeChanges(Player player);

}

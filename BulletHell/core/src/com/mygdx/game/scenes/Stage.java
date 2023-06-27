package com.mygdx.game.scenes;

import com.mygdx.game.player.Player;

public interface Stage {
    public void create();
    public void render(); //contains the basic rendering and pausing features
    public void renderContinuation(); //contains the new stage elements
    public void dispose();
}

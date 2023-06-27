
package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.backgroundAndCursor.Background;
import com.mygdx.game.player.Player;

public class Stage3 extends StageFundamental {
    private WavesStage3 ws3 = new WavesStage3();
    public Stage3(float width, float height) {
        super(width, height);
    }


    private Background bg = new Background();

    public void create(Player player) {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        this.player = player;
        ws3.createWaves(width,height,player);

        bg.createBackground(width * 1.96f, height, "Backgrounds/map3Slow.png", "Backgrounds/map3Fast.png", "Backgrounds/opacityEffect3.png");
    }
    public void renderContinuation() {

        // bg default color
        ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);

        // logic
        ws3.collisionTest();

        // rendering
        bg.renderBackground();


        ws3.renderWaves(player.getCenterX(),player.getCenterY());

        checkBossDefeat(ws3.checkEvolution());
        player.renderPlayer();
        if(ws3.isOver()){
            setIsOver(true);
        }


    }


    public void dispose() {
        bg.disposeBackground();
        ws3.dispose();

    }
}


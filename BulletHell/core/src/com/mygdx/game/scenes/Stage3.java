package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.backgroundAndCursor.Background;

public class Stage3 extends StageFundamental {
    private WavesStage3 ws3 = new WavesStage3();
    public Stage3(float width, float height) {
        super(width, height);
    }


    private Background bg = new Background();

    public void create() {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        player.createPlayer();

        ws3.createWaves(width,height,player);

        bg.createBackground(width * 1.96f, height, "Backgrounds/map1Slow.png", "Backgrounds/map1Fast.png", "Backgrounds/opacityEffect.png");
    }
    public void renderContinuation() {

        // bg default color
        ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);

        // logic
        ws3.collisionTest();

        // rendering
        bg.renderBackground();

        ws3.renderWaves(player.getCenterX(),player.getCenterY());


        player.renderPlayer();

        checkBossDefeat(ws3.checkEvolution());
        if(ws3.isOver()){
            setIsOver(true);
        }


    }


    public void dispose() {
        bg.disposeBackground();
        player.disposePlayer();
        ws3.disposeWaves();

    }
}


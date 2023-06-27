package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.backgroundAndCursor.Background;
import com.mygdx.game.player.Player;

public class Stage2 extends StageFundamental {
    private WavesStage2 ws2 = new WavesStage2();
    public Stage2(float width, float height) {
        super(width, height);
    }


    private Background bg = new Background();

    public void create(Player player) {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        this.player = player;
        ws2.createWaves(width,height,player);

        bg.createBackground(width * 1.96f, height, "Backgrounds/map2Slow.png", "Backgrounds/map2Fast.png", "Backgrounds/opacityEffect2.png");
    }
    public void renderContinuation() {

        // bg default color
        ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);

        // logic
        ws2.collisionTest();

        // rendering
        bg.renderBackground();

        ws2.renderWaves(player.getCenterX(),player.getCenterY());



        checkBossDefeat(ws2.checkEvolution());
        player.renderPlayer();
        if(ws2.isOver()){
            setIsOver(true);
        }


    }


    public void dispose() {
        bg.disposeBackground();
        ws2.dispose();

    }
}


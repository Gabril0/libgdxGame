package com.mygdx.game.bosses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.player.Player;

public class BlackHole extends BossFundamentals{
    private Player player;
    public BlackHole(float positionX, float positionY, float speedX, float speedY, float health, String bulletImg, String bulletType, String sprite) {
        super(positionX, positionY, speedX, speedY, health, bulletImg, bulletType, sprite);
        bulletPool.setCoolDown(0.5f);
        damage = 200;
        bulletPool.changeSize(10000);
    }
    public void render(float playerCenterX, float playerCenterY){
        float deltaTime = Gdx.graphics.getDeltaTime();
        batch.begin();
        if(startupAnimation){
            batch.setColor(Color.RED);
            intro.render(positionX - sizeX, positionY - sizeY, sizeX * 3, sizeY * 3,
                    0, batch);

            if (intro.getWasFinished()) {
                batch.setColor(Color.WHITE);
                startupAnimation = false;
            }
        }
        else {
            if (!isAlive) {
                batch.setColor(Color.WHITE);

                if (explosionLock) {
                    explosion.render(positionX - sizeX, positionY - sizeY, sizeX * 3, sizeY * 3,
                            0, batch);

                    if (explosion.getWasFinished()) {
                        explosionLock = false;
                    }
                }
            }
            if (isAlive) {
                player.goToPoint(getCenterX(), getCenterY());

                this.playerCenterX = player.getCenterX();
                this.playerCenterY = player.getCenterY();
                checkBounds();
                checkHealth();


                bulletPool.renderBulletPoolEnemy(positionX, positionY,
                        sizeX, sizeY, rotateToPlayer(this.playerCenterX, this.playerCenterY) - 90, damage);

                batch.draw(texture, positionX, positionY, sizeX / 2, sizeY / 2, sizeX,
                        sizeY, 1f, 1f, 0, 0, 0, texture.getWidth(),
                        texture.getHeight(), false, false);
                if (isHit) {
                    gotHitAnimation(deltaTime);
                }


                // Update the collider's position and rotation
                //drawCollider(getCollider());
                healthBar.renderHealthBar(this);

            }
        }
        batch.end();
    }

    public float getCenterX(){
        return positionX + sizeX / 2;
    }
    public float getCenterY(){
        return positionY + sizeY / 2;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}

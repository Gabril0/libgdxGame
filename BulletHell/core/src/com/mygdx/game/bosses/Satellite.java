package com.mygdx.game.bosses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.animation.Animation;
import com.mygdx.game.animation.SatelliteIdle;

public class Satellite extends BossFundamentals {
    private Animation idle;

    // for the blinking animation
    private float lastBlink = 0;
    private float blinkDuration = 0.5f;
    private float blinkCooldown = 3;

    public Satellite(float positionX, float positionY, float speedX, float speedY, float health, String bulletImg,
            String bulletType, String sprite) {
        super(positionX, positionY, speedX, speedY, health, bulletImg, bulletType, sprite);
        idle = new SatelliteIdle();
        idle.create();
        super.bulletPool.setCoolDown(0.3f);
        super.bulletPool.changeSize(2000);
    }

    @Override
    public void render(float playerCenterX, float playerCenterY) {
        float deltaTime = Gdx.graphics.getDeltaTime();
        elapsedTime += deltaTime;
        batch.begin();
        if(!isAlive){
            batch.setColor(Color.WHITE);

            if(explosionLock){
                explosion.render(positionX - sizeX, positionY - sizeY, sizeX * 3, sizeY * 3,
                        0, batch);

                if(explosion.getWasFinished()){
                    explosionLock = false;
                }
            }
        }
        if (isAlive) {

            this.playerCenterX = playerCenterX;
            this.playerCenterY = playerCenterY;
            move();
            checkBounds();
            checkHealth();

            
            bulletPool.renderBulletPoolEnemy(positionX, positionY,
                    sizeX, sizeY, rotateToPlayer(this.playerCenterX, this.playerCenterY) - 90, damage);

            if (elapsedTime > lastBlink + blinkCooldown) {
                lastBlink = elapsedTime;
            }

            if (elapsedTime <= lastBlink + blinkDuration) {
                idle.render(positionX, positionY, sizeX, sizeY, rotateToPlayer(playerCenterX, playerCenterY), batch);
            } else {
                batch.draw(texture, positionX, positionY, sizeX / 2, sizeY / 2, sizeX,
                        sizeY, 1f, 1f, rotateToPlayer(this.playerCenterX, this.playerCenterY), 0, 0, texture.getWidth(),
                        texture.getHeight(), false, false);
            }

            if (isHit) {
                gotHitAnimation(deltaTime);
            }




            // Update the collider's position and rotation
            // drawCollider(getCollider());
            healthBar.renderHealthBar(this);

        }
        batch.end();

    }

    @Override
    public void dispose() {
        texture.dispose();
        batch.dispose();
        collider.dispose();
        bulletPool.disposeBulletPool();
        healthBar.disposeHealthBar();
        idle.dispose();
    }

}

package com.mygdx.game.bosses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class Sun extends BossFundamentals{

    public Sun(float positionX, float positionY, float speedX, float speedY, float health, String bulletImg, String bulletType, String sprite) {
        super(positionX, positionY, speedX, speedY, health, bulletImg, bulletType, sprite);
        positionX -= sizeX/2;
        positionY -= sizeY/2;
        bulletPool.setCoolDown(0.7f);

        sizeX *= 2.5f;
        sizeY *= 3.4f;
    }
    @Override
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

                this.playerCenterX = playerCenterX;
                this.playerCenterY = playerCenterY;
                move();
                checkBounds();
                checkHealth();


                bulletPool.renderBulletPoolEnemy(positionX, positionY,
                        sizeX, sizeY, rotateToPlayer(this.playerCenterX, this.playerCenterY) - 90, damage);

                bulletPool.renderBulletPoolEnemy(positionX, positionY - sizeY / 4,
                        sizeX, sizeY, rotateToPlayer(this.playerCenterX, this.playerCenterY + sizeY / 4) - 90, damage);

                bulletPool.renderBulletPoolEnemy(positionX, positionY + sizeY / 2,
                        sizeX, sizeY, rotateToPlayer(this.playerCenterX - sizeY / 2, this.playerCenterY - sizeY / 2) - 90, damage);

                bulletPool.renderBulletPoolEnemy(positionX, positionY - sizeY / 4,
                        sizeX, sizeY, rotateToPlayer(this.playerCenterX, this.playerCenterY + sizeY / 4) - 90, damage);

                bulletPool.renderBulletPoolEnemy(positionX, positionY - sizeY / 2,
                        sizeX, sizeY, rotateToPlayer(this.playerCenterX, this.playerCenterY + sizeY / 2) - 90, damage);


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
}

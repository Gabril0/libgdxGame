package com.mygdx.game.bosses;

import com.badlogic.gdx.Gdx;


import com.mygdx.game.enemy.Enemy;



public class BossFundamentals extends Enemy {

    public BossFundamentals(float positionX, float positionY, float speedX, float speedY, float health,
            String bulletImg, String bulletType, String sprite) {
        super(positionX, positionY, speedX, speedY, health, bulletImg, bulletType, sprite);
        float width = Gdx.graphics.getWidth();
		//float height = Gdx.graphics.getHeight();

        //atributes
        sizeX = width/6;
        sizeY = width/6;

    }

    @Override
    public void render(float playerCenterX, float playerCenterY){
        float deltaTime = Gdx.graphics.getDeltaTime();
        if(isAlive){

            this.playerCenterX = playerCenterX;
            this.playerCenterY = playerCenterY;
            move(1f, 1);
            checkBounds();
            checkHealth();
            

            bulletPool.renderBulletPoolEnemy(positionX, positionY,
                    sizeX, sizeY, rotateToPlayer(this.playerCenterX, this.playerCenterY) - 90, damage);

            batch.begin();
            batch.draw(texture, positionX, positionY, sizeX / 2, sizeY / 2, sizeX,
                    sizeY, 1f, 1f, rotateToPlayer(this.playerCenterX, this.playerCenterY), 0, 0, texture.getWidth(),
                    texture.getHeight(), false, false);
            if(isHit){gotHitAnimation(deltaTime);}
            batch.end();

            // Update the collider's position and rotation
            //drawCollider(getCollider());
            healthBar.renderHealthBar(this);

        }

    }
}

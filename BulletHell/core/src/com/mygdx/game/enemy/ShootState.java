package com.mygdx.game.enemy;

// A state class that enables the enemy to shoot default
public class ShootState extends AbstractShootState {
    public ShootState(Enemy context) {
        super(context);
    }

    @Override
    public void shoot() {
        context.bulletPool.renderBulletPoolEnemy(context.positionX, context.positionY, 
            context.sizeX, context.sizeY, context.rotateToPlayer(context.playerCenterX, context.playerCenterY) - 90, context.damage);
    }
}

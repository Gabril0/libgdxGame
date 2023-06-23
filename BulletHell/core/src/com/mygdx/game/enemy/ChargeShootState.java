package com.mygdx.game.enemy;

public class ChargeShootState extends AbstractShootState {
    public ChargeShootState(Enemy context){
        super(context);
        context.bulletPool.setCoolDown(2.5f);
        context.bulletPool.changeSize(10000);
    }

    @Override
    public void shoot() {
        // makes it charge the shot
        context.bulletPool.renderBulletPoolEnemy(context.positionX, context.positionY, context.sizeX, context.sizeY, 
            context.rotateToPlayer(context.playerCenterX, context.playerCenterY) - 90, context.damage);
    }
}

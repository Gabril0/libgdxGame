package com.mygdx.game.enemy;

// A state class that enables the enemy to shoot in only one of all 4 basic directions
public class StaticShootState extends AbstractShootState {

    public StaticShootState(Enemy context){
        super(context);
    }


    @Override
    public void shoot() {
        context.bulletPool.setCoolDown(0.08f);
        context.bulletPool.renderBulletPoolEnemy(context.positionX, context.positionY, 
            context.sizeX, context.sizeY, POSSIBLE_SHOOTING_ANGLE[SHOOTING_ANGLE], context.damage);
    }
}

package com.mygdx.game.enemy;

// A state class that enables the enemy to shoot in all 4 basic directions randomly choosing them
public class VariableShootState extends AbstractShootState {

    public VariableShootState(Enemy context) {
        super(context);
        // change the shooting frequency
        context.bulletPool.setCoolDown(0.08f);
    }
    
    @Override
    public void shoot() {
        context.bulletPool.renderBulletPoolEnemy(context.positionX, context.positionY, 
            context.sizeX, context.sizeY, POSSIBLE_SHOOTING_ANGLE[context.random.nextInt(4)], context.damage);
    }
}

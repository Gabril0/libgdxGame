package com.mygdx.game.enemy;

// An abstract class that holds all the common code btw its subclasses
public abstract class AbstractShootState implements State {
    protected Enemy context;
    protected final int[] POSSIBLE_SHOOTING_ANGLE = {0, 90, 180, 270};
    protected final int SHOOTING_ANGLE;

    public AbstractShootState(Enemy context) {
        this.context = context;
        SHOOTING_ANGLE = context.random.nextInt(4);
    }
}

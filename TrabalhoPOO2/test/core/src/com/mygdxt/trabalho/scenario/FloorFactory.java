package com.mygdxt.trabalho.scenario;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdxt.trabalho.physics.PhysicsWorld;
import com.mygdxt.trabalho.physics.SurfacePhysics;

public class FloorFactory {
    private SurfacePhysics floorPhysics;

    // TODO: make the SurfacePhysics instantiates the BodyDef giving it's constructor the position
    public FloorFactory(PhysicsWorld world, float positionX, float positionY) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(positionX, positionY);
        floorPhysics = new SurfacePhysics(world, bodyDef);
    }
}

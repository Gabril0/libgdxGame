package com.mygdxt.trabalho.physics;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class SurfacePhysics {
    private Body body;

    // TODO: make the SurfacePhysics instantiates the BodyDef giving it's constructor the position

    // the bodyDef.position.set MUST BE provided by the client code, since we can't assume a started position
    public SurfacePhysics(PhysicsWorld world, BodyDef bodyDef) {
        // defines this body to be a StaticBody, meaning it cannot move and physics does not affect it
        bodyDef.type = BodyType.StaticBody;
        body = world.createBody(bodyDef);

        PolygonShape surfaceShape = new PolygonShape();
        // creates a box shape using half of the width and height
        surfaceShape.setAsBox(200f, 20f);
        // creates a fixture for the body using the surface shape and a density
        body.createFixture(surfaceShape, 0f);
        // frees the memory associated with the fixture, since its not needed anymore
        surfaceShape.dispose();
    }
}

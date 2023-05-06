package com.mygdxt.trabalho.physics;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;


// object responsable for managing all the physics in our game, thus, it needs to be a SINGLETON
// currently i'm trying to not use the singleton by only calling the constructor in the create method
public class PhysicsWorld {
    private World world; // this is where all the simulations occur and where all the physics and objects live
    private float step; // amount of seconds to simulate
    private int velocityIterations;
    private int positionIterations;

    // in my online researches, the default values for step, velocityIterations and positionIterations are
    // 1/60f, 6, 2, respectively. we're probably gonna stick with those
    public PhysicsWorld(World world, float step, int velocityIterations, int positionIterations) {
        this.world = world;

        this.step = step;
        this.velocityIterations = velocityIterations;
        this.positionIterations = positionIterations;
    }

    public void update(float deltaTime) {
        // updates the simulation in the world
        // its a good idea to update all the sprites before we do the step
        world.step(step, velocityIterations, positionIterations);
    }

    public void dispose() {
        world.dispose();
    }

    public Body createBody(BodyDef bodyDef) {
        // create a Box2D body in the world
        return world.createBody(bodyDef);
    }

    public PhysicsWorld get() {
        return this;
    }
    public World getWorld() {
        return world;
    }
}

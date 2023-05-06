package com.mygdxt.trabalho.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class PlayerPhysics {
    private Body body;
    private Vector2 center;

    public final float PIXELS_PER_METER = 920f;
    public final float WIDTH = 300f; // pixels
    public final float HEIGHT = 320f; // pixels

    public PlayerPhysics(PhysicsWorld world, float x, float y) {
        BodyDef bodyDef = new BodyDef();
        // makes the bodyDef be a DinamicBody, i.e. it is affected by physics and StaticBody
        bodyDef.type = BodyType.DynamicBody;
        // set the initial position of the Player
        bodyDef.position.set(x, y);

        // sets the object's physical properties 
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.05f;
        PolygonShape shape = new PolygonShape();
        // setAsBox expects half size of width and height
        shape.setAsBox(WIDTH / PIXELS_PER_METER * 2, HEIGHT / PIXELS_PER_METER * 2);
        fixtureDef.shape = shape;

        // create the body
        body = world.createBody(bodyDef);
        // applies the fixture to the object
        body.createFixture(fixtureDef);

        // frees the memory
        shape.dispose();

        center = body.getWorldCenter();
    }

    // makes the body object move in the X axis
    // negative values moves the player left and positive moves right
    public void applyForceX(float x) {
        body.applyForceToCenter(x, 0f, true);
    }
    // makes the body object move in the Y axis
    // we'll use it for jumping, since impulse acts immediately
    public void applyImpulseY(float y) {
        body.applyLinearImpulse(new Vector2(0f, y), center, false);
    }

    // gets the linear velocity of the body
    public Vector2 getVelocity() {
        return body.getLinearVelocity();
    }
    public float getWidth() {
        return WIDTH / PIXELS_PER_METER;
    }
    public float getHeight() {
        return HEIGHT / PIXELS_PER_METER;
    }
    public Vector2 getPosition() {
        return body.getPosition();
    }
}

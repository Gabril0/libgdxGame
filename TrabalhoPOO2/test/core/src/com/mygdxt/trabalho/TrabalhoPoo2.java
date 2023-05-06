package com.mygdxt.trabalho;

// This class acts like a game manager, rendering the gameObjects and disposing them

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdxt.trabalho.physics.PhysicsWorld;
import com.mygdxt.trabalho.scenario.FloorFactory;

public class TrabalhoPoo2 extends ApplicationAdapter {
	Player player = new Player();
	OrthographicCamera cam = new OrthographicCamera();
	PhysicsWorld world;
	FloorFactory floor;
	
	@Override
	public void create () { //runs everytime the program is started
		Box2D.init();
		world = new PhysicsWorld(new World(new Vector2(0, -10), true), 1/60f, 6, 2);
		floor = new FloorFactory(world, 0, 0);
		player.createPlayer(world);
	}

	@Override
	public void render () { //just like update in unity, runs every frame
		//its a good idea to put all the drawing entities here under the same batch object, so we can optimize OpenGL render
		ScreenUtils.clear(98f/255f, 112f/255f, 127f/255f, 1); //Set the background color
		player.renderPlayer();

		// render all things before we update the world (step up)
		world.update(Gdx.graphics.getDeltaTime());		
	}
	
	@Override
	public void dispose () { //is called when the program is closed
		player.disposePlayer();
		world.dispose();
	}

}

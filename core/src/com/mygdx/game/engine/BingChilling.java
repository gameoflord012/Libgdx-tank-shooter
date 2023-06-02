package com.mygdx.game.engine;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.GameScreen;
import com.mygdx.game.engine.system.destroy.EntityDestroySystem;
import com.mygdx.game.engine.system.event.EntityEventSystem;

public class BingChilling extends Game {
	public static final float SCREEN_WIDTH = 320f, SCREEN_HEIGHT = 240f;
	public OrthographicCamera camera;
	private Engine engine;
	public Engine getEngine(){return engine;}
	private static BingChilling instance;

	GameScreen gameScreen;

	private BingChilling()
	{
		engine = new Engine();
		engine.addSystem(new EntityEventSystem());
		engine.addSystem(new EntityDestroySystem());
	}

	public static BingChilling getInstance()
	{
		if(instance == null)
		{
			instance = new BingChilling();
		}
		return instance;
	}

	@Override
	public void create ()
	{
		camera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);

		this.gameScreen = new GameScreen((this));
		setScreen(gameScreen);
	}

	public boolean isPointScreen(float vx, float vy)
	{
		return !(
				vx < -SCREEN_WIDTH/2 || vx > SCREEN_WIDTH/2 ||
				vy < -SCREEN_HEIGHT/2 || vy > SCREEN_HEIGHT/2);
	}

	@Override
	public void render()
	{
		engine.update(Gdx.graphics.getDeltaTime());

		super.render();
	}
}

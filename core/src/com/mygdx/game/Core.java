package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;

import core.GE;
import core.system.input.InputSystem;
import core.system.physic.PhysicSystem;

public class Core extends Game {
	public static final float SCREEN_WIDTH = 320f, SCREEN_HEIGHT = 240f;
	public OrthographicCamera camera;

	private static Core instance;

	GE gameEngine;

	GameScreen gameScreen;

	public static Core getInstance()
	{
		if(instance == null)
		{
			instance = new Core();
		}
		return instance;
	}

	@Override
	public void create ()
	{
		gameEngine = new GE();

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
		gameEngine.update(Gdx.graphics.getDeltaTime());

		super.render();
	}

	public static PhysicSystem physic()
	{
		return getInstance().gameEngine.getSystem(PhysicSystem.class);
	}

	public static InputSystem input()
	{
		return getInstance().gameEngine.getSystem(InputSystem.class);
	}

	public static GE gameEngine()
	{
		return getInstance().gameEngine;
	}
}

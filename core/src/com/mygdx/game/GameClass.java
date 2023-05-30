package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameClass extends Game {
	public OrthographicCamera camera;
	public SpriteBatch batch;
	public ShapeRenderer shapeRenderer;
	public BitmapFont font;

	public World world;

	private static GameClass instance;

	private GameClass(){}

	public static GameClass getInstance()
	{
		if(instance == null)
		{
			instance = new GameClass();
		}
		return instance;
	}

	@Override
	public void create () {
		camera = new OrthographicCamera(320, 240);

		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		font = new BitmapFont();

		setScreen(new GameScreen(this));
	}

	public boolean isPointScreen(float vx, float vy)
	{
		return !(vx < -160 || vx > 160 || vy < -120 || vy > 120);
	}






























































































































































































	@Override
	public void dispose () {

	}
}

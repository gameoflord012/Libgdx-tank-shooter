package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen extends ScreenAdapter {
    private GameClass game;

    public GameScreen(GameClass game)
    {
        this.game = game;
    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0.25f, 0.75f, 0.5f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();

        game.font.getData().setScale(0.5f, 0.3f);
        game.font.draw(game.batch, "Tank Shooter", -40, 40);
        game.font.getData().setScale(0.3f, 0.3f);
        game.font.draw(game.batch, "--Game--", -40, 30);
        game.font.draw(game.batch, "Press 'space' to play", -40, 25);
        game.font.draw(game.batch, "'Esc' to quit", -40, 20);
        game.batch.end();
    }
}

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.mygdx.game.entities.Tank;
import com.mygdx.game.entities.target.TargetPoolFactory;
import com.mygdx.game.entities.target.TargetPoolObject;

import utility.Utility;

public class GameScreen extends ScreenAdapter {
    private Core game;


    SpriteBatch batch;
    Sound sound;

    TargetPoolFactory targetPool;

    public GameScreen(Core game)
    {
        this.game = game;
        sound = Gdx.audio.newSound(Gdx.files.internal("dnx-116856.mp3"));
        targetPool = new TargetPoolFactory();

        Core.input().addInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if(keycode == Input.Keys.SPACE)
                {
                    TargetPoolObject target = targetPool.getPoolObject(
                            Utility.getInstance().randomInt(-140, 140),
                            Utility.getInstance().randomInt(-100, 100)
                    );

                    return true;
                }

                return false;
            }
        });
    }

    @Override
    public void show()
    {
        /*long id = sound.play(1);
        sound.setLooping(id,true);*/
        CreateLevel();
    }

    private void CreateLevel() {
        Core.gameEngine().addEntity(new Tank(Input.Keys.A));
        Core.gameEngine().addEntity(new Tank(Input.Keys.J));

        targetPool.getPoolObject(50, 50);
        targetPool.getPoolObject(-50, -50);
        targetPool.getPoolObject(-50, 50);
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0.10f, 0.2f, 0.25f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void hide()
    {
        sound.stop();
    }

    @Override
    public void dispose() {

        sound.dispose();
    }
}

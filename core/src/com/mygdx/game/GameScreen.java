package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.mygdx.game.entities.Tank;
import com.mygdx.game.entities.target.TargetPoolFactory;
import com.mygdx.game.entities.target.TargetPoolObject;

import utility.Utility;

public class GameScreen extends ScreenAdapter {
    private Core game;
    private Box2DDebugRenderer debugRenderer;
    WorldLisenerRegister worldLisenerRegister;
    Sound sound;

    TargetPoolFactory targetPool;

    public GameScreen(Core game)
    {
        this.game = game;
        debugRenderer = new Box2DDebugRenderer();
        worldLisenerRegister = new WorldLisenerRegister(Core.physic().getWorld());
        sound = Gdx.audio.newSound(Gdx.files.internal("dnx-116856.mp3"));
        targetPool = new TargetPoolFactory(worldLisenerRegister);

        InputLisenerRegister.getInstance().addInputProcessor(new InputProcessor() {
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

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(float amountX, float amountY) {
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
        //Core.gameEngine().addEntity(new Tank(Input.Keys.J));

        targetPool.getPoolObject(50, 50);
        targetPool.getPoolObject(-50, -50);
        targetPool.getPoolObject(-50, 50);
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0f, 0f, 0f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        debugRenderer.render(Core.physic().getWorld(), game.camera.combined);
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

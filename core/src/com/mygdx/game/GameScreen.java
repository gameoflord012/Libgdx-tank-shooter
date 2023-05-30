package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.gameObjects.GameEntity;
import com.mygdx.game.gameObjects.Tank;
import com.mygdx.game.gameObjects.target.Target;
import com.mygdx.game.gameObjects.target.TargetPoolFactory;
import com.mygdx.game.gameObjects.target.TargetPoolObject;

public class GameScreen extends ScreenAdapter {
    private GameClass game;
    private Box2DDebugRenderer debugRenderer;
    WorldLisenerRegister worldLisenerRegister;
    World world;
    Tank tank;
    Sound sound;

    TargetPoolFactory targetPool;

    public GameScreen(GameClass game)
    {
        this.game = game;
        debugRenderer = new Box2DDebugRenderer();
        world = new World(new Vector2(0, 0), true);
        worldLisenerRegister = new WorldLisenerRegister(world);
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
        new Tank(world, Input.Keys.A);
        new Tank(world, Input.Keys.J);

        targetPool.getPoolObject(50, 50);
        targetPool.getPoolObject(-50, -50);
        targetPool.getPoolObject(-50, 50);
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0f, 0f, 0f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(delta, 3, 3);

        GameEntity.updateAll(delta);

        debugRenderer.render(world, game.camera.combined);
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

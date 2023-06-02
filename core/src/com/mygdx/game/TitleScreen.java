package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.engine.BingChilling;

public class TitleScreen extends ScreenAdapter {
    /*private BingChilling game;

    public TitleScreen(BingChilling game)
    {
        this.game = game;
    }

    public Sound sound;
    @Override
    public void show()
    {
        Gdx.input.setInputProcessor(new InputAdapter()
        {
            @Override
            public boolean keyDown(int keyCode)
            {
                if(Input.Keys.SPACE == keyCode)
                {
                    game.setScreen(new GameScreen(game));
                }

                if(Input.Keys.ESCAPE == keyCode)
                {
                    Gdx.app.exit();
                }
                return true;
            }
        });

        sound = Gdx.audio.newSound(Gdx.files.internal("sciense-inspiring-technology-113441.mp3"));
        long id = sound.play(1);
        sound.setLooping(id,true);
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0.25f, 0.75f, 0.5f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);

        Texture texture = new Texture(Gdx.files.internal("16m4grmxq22z6mao.jpg"));

        game.batch.begin();
        game.batch.draw(texture, -160, -120, 320, 240);
        game.font.getData().setScale(1.2f, 1.2f);
        game.font.setColor(Color.GREEN);
        game.font.draw(game.batch, "Tank Shooter", -80, 40);
        //game.font.getData().setScale(0.3f, 0.3f);
        game.font.draw(game.batch, "--Main menu--", -80, 20);
        game.font.draw(game.batch, "Press 'space' to play", -80, 0);
        game.font.draw(game.batch, "'Esc' to quit", -80, -20);
        game.batch.end();
    }

    @Override
    public void hide()
    {
        sound.dispose();
        Gdx.input.setInputProcessor(null);
    }*/
}

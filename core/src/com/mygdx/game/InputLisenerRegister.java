package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import java.util.HashSet;
import java.util.Set;

public class InputLisenerRegister
{
    private InputLisenerRegister(){}
    static InputLisenerRegister instance;
    public static InputLisenerRegister getInstance()
    {
        if(instance == null) instance = new InputLisenerRegister();
        return instance;
    }


    static
    {
        Gdx.input.setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                for (InputProcessor inputProcessor : getInstance().liseners)
                {
                    if(inputProcessor.keyDown(keycode))
                    {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                for (InputProcessor inputProcessor : getInstance().liseners)
                {
                    if(inputProcessor.keyUp(keycode))
                    {
                        return true;
                    }
                }
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
    Set<InputProcessor> liseners = new HashSet<InputProcessor>();

    public void addInputProcessor(InputProcessor inputProcessor)
    {
        liseners.add(inputProcessor);
    }
}

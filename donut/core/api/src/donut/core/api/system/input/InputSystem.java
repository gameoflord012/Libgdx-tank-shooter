package donut.core.api.system.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;

import java.util.HashSet;
import java.util.Set;

import donut.core.api.system.EntitySystemWrapable;
import donut.core.api.system.EntitySystemWrapper;

public class InputSystem extends EntitySystemWrapper<InputSystem> {

    Set<InputProcessor> inputProcessors = new HashSet<>();

    protected static class Wrapable extends EntitySystemWrapable<InputSystem>
    {
    }

    public InputSystem()
    {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                for(InputProcessor inputProcessor : getInputProcessors())
                {
                    if(inputProcessor.keyDown(keycode)) return true;
                }

                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                for(InputProcessor inputProcessor : getInputProcessors())
                {
                    if(inputProcessor.keyUp(keycode)) return true;
                }

                return false;
            }
        });
    }
    @Override
    protected EntitySystemWrapable<InputSystem> getWrappable()
    {
        return new Wrapable();
    }

    public void addInputProcessor(InputProcessor inputProcessor)
    {
        inputProcessors.add(inputProcessor);
    }

    public InputProcessor[] getInputProcessors()
    {
        inputProcessors.remove(null);
        return inputProcessors.toArray(new InputProcessor[0]);
    }
}

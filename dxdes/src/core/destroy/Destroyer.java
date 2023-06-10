package core.destroy;

import com.badlogic.ashley.core.Component;

import core.ComponentWrapable;
import core.ComponentWrapper;

public class Destroyer extends ComponentWrapper<Destroyer> {
    public static class Wrapable extends ComponentWrapable<Destroyer>
    {

    }
    @Override
    protected ComponentWrapable<Destroyer> getWrappable()
    {
        return new Wrapable();
    }
}

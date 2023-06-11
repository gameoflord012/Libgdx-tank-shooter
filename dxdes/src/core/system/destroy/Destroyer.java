package core.system.destroy;

import core.component.ComponentWrapable;
import core.component.ComponentWrapper;

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

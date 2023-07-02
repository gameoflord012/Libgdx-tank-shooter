package donut.api.core.system.destroy;

import donut.api.core.component.ComponentWrapable;
import donut.api.core.component.ComponentWrapper;

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

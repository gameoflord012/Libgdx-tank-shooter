package donut.core.api.system.destroy;

import donut.core.api.component.ComponentWrapable;
import donut.core.api.component.ComponentWrapper;

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

package core.system.debug;

import core.ComponentWrapable;
import core.ComponentWrapper;

public class Debugger extends ComponentWrapper<Debugger> {
    public static class Wrapable extends ComponentWrapable<Debugger>
    {

    }

    @Override
    protected ComponentWrapable<Debugger> getWrappable()
    {
        return new Wrapable();
    }

    public String debugText = "Debug me!";
}

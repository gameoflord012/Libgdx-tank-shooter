package core.system.debug;

import com.badlogic.ashley.core.ComponentMapper;

import core.component.ComponentWrapable;
import core.component.ComponentWrapper;

public class Debugger extends ComponentWrapper<Debugger> {
    public static ComponentMapper<Debugger.Wrapable> mapper = ComponentMapper.getFor(Debugger.Wrapable.class);
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

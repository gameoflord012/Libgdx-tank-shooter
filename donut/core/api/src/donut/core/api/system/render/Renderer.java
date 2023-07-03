package donut.core.api.system.render;

import donut.core.api.component.ComponentWrapable;
import donut.core.api.component.ComponentWrapper;

import com.badlogic.ashley.core.ComponentMapper;

import java.util.HashSet;
import java.util.Set;

public class Renderer extends ComponentWrapper<Renderer> {
    public static ComponentMapper<Renderer.Wrapable> mapper = ComponentMapper.getFor(Renderer.Wrapable.class);

    public static class Wrapable extends ComponentWrapable<Renderer>
    {}

    @Override
    protected ComponentWrapable<Renderer> getWrappable() {
        return new Wrapable();
    }

    private final Set<IRenderCallback> renderLiseners = new HashSet<>();

    public void addRenderCallback(IRenderCallback renderCallback)
    {
        renderLiseners.add(renderCallback);
    }

    public IRenderCallback[] getRenderCallBacks()
    {
        renderLiseners.remove(null);
        return renderLiseners.toArray(new IRenderCallback[0]);
    }
}

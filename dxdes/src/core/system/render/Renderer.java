package core.system.render;

import core.component.ComponentCreator;
import core.component.ComponentWrapable;
import core.component.ComponentWrapper;
import core.system.EntitySystemWrapper;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

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

    public final SpriteBatch batch;
    public final ShapeRenderer shapeRenderer;
    private final Set<IRenderCallback> renderkLiseners = new HashSet<>();

    protected Renderer(SpriteBatch batch, ShapeRenderer shapeRenderer)
    {
        this.batch = batch;
        this.shapeRenderer = shapeRenderer;
    }

    public void addRenderCallback(IRenderCallback renderCallback)
    {
        renderkLiseners.add(renderCallback);
    }

    public IRenderCallback[] getRenderCallBacks()
    {
        renderkLiseners.remove(null);
        return renderkLiseners.toArray(new IRenderCallback[0]);
    }
}

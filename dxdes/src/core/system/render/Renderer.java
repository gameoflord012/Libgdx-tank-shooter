package core.system.render;

import core.ComponentWrapable;
import core.ComponentWrapper;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

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
    protected IRenderCallback renderCallback;

    protected Renderer(SpriteBatch batch, ShapeRenderer shapeRenderer, IRenderCallback renderCallback)
    {
        this.batch = batch;
        this.shapeRenderer = shapeRenderer;
        this.renderCallback = renderCallback;
    }
}

package core.system.debug;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import core.component.ComponentWrapable;
import core.component.ComponentWrapper;
import core.system.render.IRenderCallback;
import core.system.render.Renderer;

public class Debugger extends ComponentWrapper<Debugger> implements IRenderCallback {
    public static ComponentMapper<Debugger.Wrapable> mapper = ComponentMapper.getFor(Debugger.Wrapable.class);

    private boolean hasRenderer = false;
    public boolean isHasRenderer()
    {
        return hasRenderer;
    }

    @Override
    public void render(float deltatime, SpriteBatch batch, ShapeRenderer shapeRenderer)
    {
        hasRenderer = true;

        BitmapFont font = new BitmapFont();
        batch.begin();
        font.draw(batch, debugText, 50, 50);
        batch.end();
    }

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

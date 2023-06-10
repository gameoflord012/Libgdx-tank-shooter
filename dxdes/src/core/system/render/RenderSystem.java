package core.system.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import core.system.EntitySystemWrapable;
import core.system.EntitySystemWrapper;
import utility.IWrapable;

public class RenderSystem extends EntitySystemWrapper<RenderSystem> {

    public class Wrapable extends EntitySystemWrapable<RenderSystem>
    {
        @Override
        public void update(float deltaTime) {
            for(Entity entity :
                    getEngine().getEntitiesFor(Family.all(Renderer.Wrapable.class).get()))
            {
                Renderer renderer = entity.getComponent(Renderer.Wrapable.class).getWrapper();
                renderer.renderCallback.render(deltaTime);
            }
        }
    }

    @Override
    protected EntitySystemWrapable<RenderSystem> getWrappable()
    {
        return new Wrapable();
    }

    public final SpriteBatch batch;
    public final ShapeRenderer shapeRenderer;

    public RenderSystem()
    {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
    }

    public Renderer getRenderer(IRenderCallback renderCallback)
    {
        return new Renderer(batch, shapeRenderer, renderCallback);
    }
}

package core.system.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import core.component.ComponentCreator;
import core.system.EntitySystemWrapable;
import core.system.EntitySystemWrapper;

public class RenderSystem extends EntitySystemWrapper<RenderSystem> {

    public static class Wrapable extends EntitySystemWrapable<RenderSystem>
    {
        @Override
        public void update(float deltaTime) {
            for(Entity entity :
                    getEngine().getEntitiesFor(Family.all(Renderer.Wrapable.class).get()))
            {
                Renderer renderer = entity.getComponent(Renderer.Wrapable.class).getWrapper();

                for(IRenderCallback renderCallback : renderer.getRenderCallBacks())
                {
                    renderCallback.render(deltaTime);
                }
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

        addCreator(new ComponentCreator<Renderer>(Renderer.class) {
            @Override
            public Renderer create() {
                return new Renderer(batch, shapeRenderer);
            }
        });
    }

    public Renderer getRenderer()
    {
        return new Renderer(batch, shapeRenderer);
    }
}

package donut.core.api.system.render;

import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import donut.core.api.GameEntity;
import donut.core.api.system.EntitySystemWrapable;
import donut.core.api.system.EntitySystemWrapper;
import donut.core.api.system.physic.PhysicSystem;

public class RenderSystem extends EntitySystemWrapper<RenderSystem> {

    public class Wrapable extends EntitySystemWrapable<RenderSystem>
    {
        @Override
        public void update(float deltaTime) {
            for(GameEntity entity :
                    RenderSystem.this.getEngine().getEntitiesFor(Family.all(Renderer.Wrapable.class).get()))
            {
                Renderer renderer = entity.getComponent(Renderer.class);

                for(IRenderCallback renderCallback : renderer.getRenderCallBacks())
                {
                    renderCallback.render(deltaTime, batch, shapeRenderer);
                }
            }

            debugRenderer.render(
                    RenderSystem.this.getEngine().getSystem(PhysicSystem.class).getWorld(),
                    camera.combined);
        }
    }

    @Override
    protected EntitySystemWrapable<RenderSystem> getWrappable()
    {
        return new Wrapable();
    }

    public final SpriteBatch batch;
    public final ShapeRenderer shapeRenderer;
    private Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
    public final OrthographicCamera camera;

    public RenderSystem()
    {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera(320, 240);
    }
}

package core.system.debug;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import core.GameEntity;
import core.component.ComponentCreator;
import core.system.EntitySystemWrapable;
import core.system.EntitySystemWrapper;
import core.system.render.IRenderCallback;
import core.system.render.RenderSystem;
import core.system.render.Renderer;
import core.system.render.RendererCreator;

public class DebugSystem extends EntitySystemWrapper<DebugSystem>
{
    public class Wrapable extends EntitySystemWrapable<DebugSystem>
    {
        @Override
        public void update(float deltaTime) {
            super.update(deltaTime);

            for(GameEntity entity : DebugSystem.this.getEngine().getEntitiesFor(Family.all(Debugger.Wrapable.class).get()))
            {
                Debugger debugger = entity.getComponent(Debugger.class);
                Renderer renderer = entity.requireComponent(RendererCreator.class);

                if(!debugger.isHasRenderer())
                    renderer.addRenderCallback(debugger);
            }
        }
    }

    @Override
    protected EntitySystemWrapable<DebugSystem> getWrappable()
    {
        return new Wrapable();
    }
}

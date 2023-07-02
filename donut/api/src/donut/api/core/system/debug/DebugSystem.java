package donut.api.core.system.debug;

import com.badlogic.ashley.core.Family;
import donut.api.core.GameEntity;
import donut.api.core.system.EntitySystemWrapable;
import donut.api.core.system.EntitySystemWrapper;
import donut.api.core.system.render.Renderer;
import donut.api.core.system.render.RendererCreator;

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

                if(debugger.isHasRenderer()) continue;

                Renderer renderer = entity.requireComponent(RendererCreator.class);
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

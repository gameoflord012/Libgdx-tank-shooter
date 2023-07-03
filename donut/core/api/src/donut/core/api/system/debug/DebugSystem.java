package donut.core.api.system.debug;

import com.badlogic.ashley.core.Family;
import donut.core.api.GameEntity;
import donut.core.api.system.EntitySystemWrapable;
import donut.core.api.system.EntitySystemWrapper;
import donut.core.api.system.render.Renderer;
import donut.core.api.system.render.RendererCreator;

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

package core.system.debug;

import com.badlogic.ashley.core.Family;
import core.GameEntity;
import core.system.EntitySystemWrapable;
import core.system.EntitySystemWrapper;
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

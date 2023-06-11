package core.system.debug;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import core.component.ComponentCreator;
import core.system.EntitySystemWrapable;
import core.system.EntitySystemWrapper;
import core.system.render.IRenderCallback;
import core.system.render.RenderSystem;
import core.system.render.Renderer;

public class DebugSystem extends EntitySystemWrapper<DebugSystem>
{
    public class Wrapable extends EntitySystemWrapable<DebugSystem>
    {
        Debugger debugger;
        Renderer renderer;

        @Override
        public void update(float deltaTime) {
            super.update(deltaTime);

            for(Entity entity : getEngine().getEntitiesFor(Family.all(Debugger.Wrapable.class).get()))
            {
                debugger = Debugger.mapper.get(entity).getWrapper();

                if(Renderer.mapper.get(entity) == null)
                {
                    renderer = DebugSystem.this.getEngine().
                            getSystem(RenderSystem.class).
                            getCreator(Renderer.class).create();

                    entity.add(renderer.wrapable);
                }
                else
                {
                    renderer = Renderer.mapper.get(entity).getWrapper();
                }

                if(!debugger.isHasRenderer())
                    renderer.addRenderCallback(debugger);
            }
        }
    }
    
    public DebugSystem()
    {
        addCreator(new ComponentCreator<Debugger>(Debugger.class) {
            @Override
            public Debugger create() {
                return new Debugger();
            }
        });
    }

    @Override
    protected EntitySystemWrapable<DebugSystem> getWrappable()
    {
        return new Wrapable();
    }
}

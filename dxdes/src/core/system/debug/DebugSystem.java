package core.system.debug;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import core.component.ComponentCreator;
import core.system.EntitySystemWrapable;
import core.system.EntitySystemWrapper;
import core.system.render.IRenderCallback;
import core.system.render.RenderSystem;
import core.system.render.Renderer;

public class DebugSystem extends EntitySystemWrapper<DebugSystem>
{
    public class Wrapable extends EntitySystemWrapable<DebugSystem> implements IRenderCallback
    {
        Debugger debugger;
        Renderer renderer;

        @Override
        public void update(float deltaTime) {
            super.update(deltaTime);

            for(Entity entity : getEngine().getEntitiesFor(Family.all(Debugger.Wrapable.class).get()))
            {
                debugger = Debugger.mapper.get(entity).getWrapper();
                renderer = Renderer.mapper.get(entity).getWrapper();

                if(renderer == null)
                {
                    renderer = DebugSystem.this.getEngine().
                            getSystem(RenderSystem.class).
                            getCreator(Renderer.class).create();
                }

                renderer.addRenderCallback(Wrapable.this);
            }
        }

        @Override
        public void render(float deltatime) {
            BitmapFont font = new BitmapFont();
            renderer.batch.begin();
            font.draw(renderer.batch, debugger.debugText, 0, 0);
            renderer.batch.end();
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

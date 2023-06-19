package core.system.render;

import core.GameEntity;
import core.component.ComponentCreator;
import core.system.debug.DebugSystem;

public class RendererCreator extends ComponentCreator<Renderer, RenderSystem>
{
    @Override
    public Renderer createDefault(RenderSystem system, GameEntity entity)
    {
        return new Renderer();
    }

    @Override
    public Class<RenderSystem> getSystemClass()
    {
        return RenderSystem.class;
    }

    @Override
    public Class<Renderer> getComponentClass()
    {
        return Renderer.class;
    }
}

package donut.api.core.system.render;

import donut.api.core.GameEntity;
import donut.api.core.component.ComponentCreator;

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

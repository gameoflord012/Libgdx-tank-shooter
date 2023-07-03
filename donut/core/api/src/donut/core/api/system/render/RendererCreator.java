package donut.core.api.system.render;

import donut.core.api.GameEntity;
import donut.core.api.component.ComponentCreator;

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

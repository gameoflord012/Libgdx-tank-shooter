package core;

import com.badlogic.ashley.core.Entity;

import core.system.destroy.Destroyer;

public class GameEntity
{
    public final Entity base;

    public GameEntity()
    {
        base = new Entity();
    }

    public GameEntity add(ComponentWrapper<?> component)
    {
        base.add(component.wrapable);
        return this;
    }

    public void Destroy()
    {
        add(new Destroyer());
    }
}

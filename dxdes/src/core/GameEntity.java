package core;

import com.badlogic.ashley.core.Entity;

import core.component.ComponentWrapper;
import core.component.Transform;
import core.system.destroy.Destroyer;

public class GameEntity
{
    public final Entity base;
    public final Transform transform;


    public GameEntity()
    {
        base = new Entity();
        transform = new Transform();
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

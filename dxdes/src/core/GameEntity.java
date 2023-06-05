package core;

import com.badlogic.ashley.core.Entity;
import systems.destroy.Destroyer;

public class GameEntity
{
    public final Entity base;

    public GameEntity()
    {
        base = new Entity();
    }

    public GameEntity add(ComponentWrapper component)
    {
        base.add(component.base);
        return this;
    }

    public void Destroy()
    {
        base.add(new Destroyer());
    }
}

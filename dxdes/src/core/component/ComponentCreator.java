package core.component;

import core.system.EntitySystemWrapper;

public abstract class ComponentCreator<ComponentType>
{
    public final Class<ComponentType> type;
    public abstract ComponentType create();

    public ComponentCreator(Class<ComponentType> type)
    {
        this.type = type;
    }
}

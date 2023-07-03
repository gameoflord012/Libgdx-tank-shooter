package donut.core.api.component;

import donut.core.api.GameEntity;
import donut.core.api.system.EntitySystemWrapper;

public abstract class ComponentCreator<ComponentType extends ComponentWrapper<ComponentType>, SystemType extends EntitySystemWrapper<SystemType>>
{
    protected ComponentCreator(){}

    public abstract ComponentType createDefault(SystemType system, GameEntity entity);
    public abstract Class<SystemType> getSystemClass();
    public abstract Class<ComponentType> getComponentClass();
}

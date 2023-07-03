package donut.core.api.component;


import donut.core.api.GameEntity;
import donut.core.api.utility.BaseWrapper;

public abstract class ComponentWrapper<ComponentType> extends
        BaseWrapper<ComponentType, ComponentWrapable<ComponentType>>
{
    public void onComponentAdded(GameEntity gameEntity) {}
}


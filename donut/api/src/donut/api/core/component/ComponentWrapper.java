package donut.api.core.component;


import donut.api.core.GameEntity;
import donut.api.utility.BaseWrapper;

public abstract class ComponentWrapper<ComponentType> extends
        BaseWrapper<ComponentType, ComponentWrapable<ComponentType>>
{
    public void onComponentAdded(GameEntity gameEntity) {}
}


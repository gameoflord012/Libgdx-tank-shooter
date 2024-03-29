package core.component;


import core.GameEntity;
import utility.BaseWrapper;

public abstract class ComponentWrapper<ComponentType> extends
        BaseWrapper<ComponentType, ComponentWrapable<ComponentType>>
{
    public void onComponentAdded(GameEntity gameEntity) {}
}


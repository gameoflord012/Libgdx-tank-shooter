package core;


import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentType;

import utility.BaseWrapper;
import utility.IWrapable;

public abstract class ComponentWrapper<ComponentType> extends
        BaseWrapper<ComponentType, ComponentWrapable<ComponentType>>
{
}


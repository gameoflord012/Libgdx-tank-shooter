package core.system;

import com.badlogic.ashley.core.ComponentType;
import com.badlogic.ashley.core.Engine;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import core.GE;
import core.component.ComponentCreator;
import utility.BaseWrapper;

public abstract class EntitySystemWrapper<SystemType extends  EntitySystemWrapper<SystemType>> extends
        BaseWrapper<SystemType, EntitySystemWrapable<SystemType>>
{
    public GE getEngine()
    {
        return ((GE.Wrappable)wrapable.getEngine()).getWrapper();
    }

    private Map<String, ComponentCreator<?>> creators = new HashMap<>();

    public void addCreator(ComponentCreator<?> creator)
    {
        creators.put(creator.type.toString(), creator);
    }

    public <T> ComponentCreator<T> getCreator(Class<T> componentClass)
    {
        return (ComponentCreator<T>)creators.get(componentClass.toString());
    }
}

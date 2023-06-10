package core.system;

import com.badlogic.ashley.core.Engine;

import utility.BaseWrapper;

public abstract class EntitySystemWrapper<SystemType> extends
        BaseWrapper<SystemType, EntitySystemWrapable<SystemType>>
{
    public Engine getEngine()
    {
        return wrapable.getEngine();
    }
}

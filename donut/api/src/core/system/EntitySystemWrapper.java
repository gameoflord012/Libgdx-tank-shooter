package core.system;

import core.GE;
import utility.BaseWrapper;

public abstract class EntitySystemWrapper<SystemType extends  EntitySystemWrapper<SystemType>> extends
        BaseWrapper<SystemType, EntitySystemWrapable<SystemType>>
{
    public GE getEngine()
    {
        return ((GE.Wrappable)wrapable.getEngine()).getWrapper();
    }
}

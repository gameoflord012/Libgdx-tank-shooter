package donut.api.core.system;

import donut.api.core.GE;
import donut.api.utility.BaseWrapper;

public abstract class EntitySystemWrapper<SystemType extends  EntitySystemWrapper<SystemType>> extends
        BaseWrapper<SystemType, EntitySystemWrapable<SystemType>>
{
    public GE getEngine()
    {
        return ((GE.Wrappable)wrapable.getEngine()).getWrapper();
    }
}

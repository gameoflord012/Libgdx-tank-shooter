package donut.core.api.system;

import donut.core.api.GE;
import donut.core.api.utility.BaseWrapper;

public abstract class EntitySystemWrapper<SystemType extends  EntitySystemWrapper<SystemType>> extends
        BaseWrapper<SystemType, EntitySystemWrapable<SystemType>>
{
    public GE getEngine()
    {
        return ((GE.Wrappable)wrapable.getEngine()).getWrapper();
    }
}

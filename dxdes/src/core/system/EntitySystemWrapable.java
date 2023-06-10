package core.system;

import utility.IWrapable;

public abstract class EntitySystemWrapable<SystemType> extends com.badlogic.ashley.core.EntitySystem implements
        IWrapable<SystemType>
{
    private SystemType wrapper;

    @Override
    public void setWrapper(SystemType wrapper) {
        this.wrapper =  wrapper;
    }

    @Override
    public SystemType getWrapper() {
        return wrapper;
    }
}

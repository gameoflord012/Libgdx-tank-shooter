package donut.core.api.component;

import com.badlogic.ashley.core.Component;

import donut.core.api.utility.IWrapable;

public class ComponentWrapable<WrapperType> implements Component, IWrapable<WrapperType>
{
    private WrapperType wrapper;

    @Override
    public void setWrapper(WrapperType wrapper)
    {
        this.wrapper = wrapper;
    }

    @Override
    public WrapperType getWrapper()
    {
        return wrapper;
    }
}
package donut.core.api.utility;

public abstract class BaseWrapper<WrapperType, WrapableType extends IWrapable<WrapperType>>
{
    public final WrapableType wrapable;
    protected abstract WrapableType getWrappable();

    public BaseWrapper()
    {
        this.wrapable = getWrappable();
        this.wrapable.setWrapper((WrapperType)this);
    }
}

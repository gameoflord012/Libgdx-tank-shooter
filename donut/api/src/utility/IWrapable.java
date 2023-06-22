package utility;

public interface IWrapable<WrapperType>
{
    public void setWrapper(WrapperType wrapper);
    public WrapperType getWrapper();
}

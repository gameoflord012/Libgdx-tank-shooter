package core;

public abstract class ComponentWrapper
{
    public static class Component implements com.badlogic.ashley.core.Component
    {
        private final ComponentWrapper wrapper;

        public Component(ComponentWrapper wrapper)
        {
            this.wrapper = wrapper;
        }

        public <T extends ComponentWrapper> T getWrapper()
        {
            return (T)wrapper;
        }
    }

    Component base;
    public ComponentWrapper()
    {
        base = new Component(this);
    }

    public void onComponentRemove(){}
}

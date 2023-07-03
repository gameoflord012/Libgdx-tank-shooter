package donut.core.api.system.event;

import com.badlogic.ashley.core.ComponentMapper;

import donut.core.api.component.ComponentWrapable;
import donut.core.api.component.ComponentWrapper;

public class EntityCallbackReceiver extends ComponentWrapper<EntityCallbackReceiver> {
    public static ComponentMapper<EntityCallbackReceiver.Wrapable> mapper =
            ComponentMapper.getFor(EntityCallbackReceiver.Wrapable.class);

    public static class Wrapable extends ComponentWrapable<EntityCallbackReceiver> {}
    @Override
    protected ComponentWrapable<EntityCallbackReceiver> getWrappable() {
        return new Wrapable();
    }

    private IUpdateCallback update;
    private ICreateCallback create;

    public void onUpdate(float delta)
    {
        update.onUpdate(delta);
    }

    public void onCreate()
    {
        create.onCreate();
    }

    public EntityCallbackReceiver setUpdater(IUpdateCallback updater)
    {
        this.update = updater;
        return this;
    }

    public EntityCallbackReceiver setCreator(ICreateCallback creater)
    {
        this.create = creater;
        return this;
    }
}

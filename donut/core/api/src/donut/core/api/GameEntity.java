package donut.core.api;

import donut.core.api.component.ComponentCreator;
import donut.core.api.component.ComponentWrapper;
import donut.core.api.component.Transform;
import donut.core.api.system.EntitySystemWrapper;
import donut.core.api.system.destroy.Destroyer;
import donut.core.api.utility.BaseWrapper;
import donut.core.api.utility.IWrapable;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public abstract class GameEntity extends BaseWrapper<GameEntity, GameEntity.Wrapable>
{
    public static class Wrapable extends com.badlogic.ashley.core.Entity implements IWrapable<GameEntity>
    {
        private GameEntity wrapper;

        @Override
        public void setWrapper(GameEntity wrapper)
        {
            this.wrapper = wrapper;
        }

        @Override
        public GameEntity getWrapper()
        {
            return wrapper;
        }
    }

    private GE gameEngine;

    public void onGameEntityAdded(GE gameEngine)
    {
        this.gameEngine = gameEngine;
    }

    public GE getEngine()
    {
        return gameEngine;
    }

    private Map<String, ComponentWrapper<?>> componentWrapperMap = new HashMap<>();

    @Override
    protected Wrapable getWrappable()
    {
        return new Wrapable();
    }

    public final Transform transform;

    public GameEntity()
    {
        transform = new Transform();
    }

    public GameEntity add(ComponentWrapper<?> component)
    {
        wrapable.add(component.wrapable);

        componentWrapperMap.put(
                component.getClass().toString(),
                component);

        component.onComponentAdded(this);
        return this;
    }

    public <T extends ComponentWrapper<T>> T getComponent(Class<T> componentWrapperClass)
    {
        return (T)componentWrapperMap.get(componentWrapperClass.toString());
    }

    public void Destroy()
    {
        add(new Destroyer());
    }

    public <C extends ComponentWrapper<C>, S extends EntitySystemWrapper<S>, Ctor extends ComponentCreator<C, S>> C requireComponent(Class<Ctor> componentCreatorClass)
    {
        ComponentCreator<C, S> componentCreator = null;
        try
        {
            componentCreator = componentCreatorClass.getConstructor().newInstance();
        } catch (NoSuchMethodException e)
        {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e)
        {
            throw new RuntimeException(e);
        } catch (InstantiationException e)
        {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }

        C component = getComponent(componentCreator.getComponentClass());

        if(component == null)
        {
            component = componentCreator.createDefault(getEngine().getSystem(componentCreator.getSystemClass()), this);
            add(component);
        }

        return component;
    }
}

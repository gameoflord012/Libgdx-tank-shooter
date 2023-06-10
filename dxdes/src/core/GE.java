package core;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;

import java.util.HashMap;
import java.util.Map;

import core.system.EntitySystemWrapable;
import core.system.EntitySystemWrapper;
import core.destroy.EntityDestroySystem;
import core.event.EntityEventSystem;
import core.physic.PhysicSystem;

public class GE
{
    public Engine base;
    Map<String, EntitySystemWrapper<?>> systemMapper = new HashMap<>();

    private void addSystem(EntitySystemWrapper<?> systemWrapper)
    {
        systemMapper.put(systemWrapper.getClass().toString(), systemWrapper);
        base.addSystem(systemWrapper.wrapable);
    }

    public GE()
    {
        base = new Engine();

        addSystem(new PhysicSystem());
        addSystem(new EntityEventSystem());
        addSystem(new EntityDestroySystem());
    }

    public void addEntity(GameEntity entity)
    {
        base.addEntity(entity.base);
    }

    public void update(float deltatime)
    {
        base.update(deltatime);
    }

    public <T extends EntitySystemWrapper<?>> T getSystem(Class<T> systemClass)
    {
        return (T)systemMapper.get(systemClass.toString());
    }
}

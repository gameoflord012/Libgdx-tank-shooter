package core;

import com.badlogic.ashley.core.Engine;

import java.util.HashMap;
import java.util.Map;

import core.system.EntitySystemWrapper;
import core.system.destroy.EntityDestroySystem;
import core.system.event.EntityEventSystem;
import core.system.input.InputSystem;
import core.system.physic.PhysicSystem;

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

        addSystem(new InputSystem());
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

package core;

import com.badlogic.ashley.core.Engine;

import java.util.HashMap;
import java.util.Map;

import systems.EntitySystemWrapper;
import systems.destroy.EntityDestroySystem;
import systems.event.EntityEventSystem;
import systems.physic.PhysicSystem;

public class GE
{
    public Engine base;
    Map<Class, EntitySystemWrapper> systemMapper = new HashMap<>();

    private void addSystem(EntitySystemWrapper systemWrapper)
    {
        systemMapper.put(systemWrapper.getClass(), systemWrapper);
        base.addSystem(systemWrapper.base);
    }

    public GE()
    {
        base = new Engine();
        addSystem(new EntityEventSystem());
        addSystem(new EntityDestroySystem());
        addSystem(new PhysicSystem());
    }

    public void addEntity(GameEntity entity)
    {
        base.addEntity(entity.base);
    }

    public void update(float deltatime)
    {
        base.update(deltatime);
    }

    public <T extends EntitySystemWrapper> T getSystem(Class<T> systemClass)
    {
        return (T)systemMapper.get(systemClass);
    }
}

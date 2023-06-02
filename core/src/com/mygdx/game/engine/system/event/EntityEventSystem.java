package com.mygdx.game.engine.system.event;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

public class EntityEventSystem extends EntitySystem
{
    ComponentMapper<EntityCallbackReceiver> mapper = ComponentMapper.getFor(EntityCallbackReceiver.class);

    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> entites =
                getEngine().getEntitiesFor(Family.all(EntityCallbackReceiver.class).get());

        for(Entity entity : entites)
        {
            EntityCallbackReceiver eventComp = mapper.get(entity);
            eventComp.update.onUpdate(deltaTime);
        }
    }
}

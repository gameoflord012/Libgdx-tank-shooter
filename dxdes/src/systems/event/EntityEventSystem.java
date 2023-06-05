package systems.event;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import systems.EntitySystemWrapper;

public class EntityEventSystem extends EntitySystemWrapper
{
    ComponentMapper<EntityCallbackReceiver.Component> mapper =
            ComponentMapper.getFor(EntityCallbackReceiver.Component.class);

    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> entites =
                getEngine().getEntitiesFor(Family.all(EntityCallbackReceiver.Component.class).get());

        for(Entity entity : entites)
        {
            EntityCallbackReceiver eventComp = mapper.get(entity).getWrapper();
            eventComp.update.onUpdate(deltaTime);
        }
    }
}

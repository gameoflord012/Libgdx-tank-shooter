package core.event;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import core.ComponentWrapable;
import core.system.EntitySystemWrapable;
import core.system.EntitySystemWrapper;
import utility.IWrapable;

public class EntityEventSystem extends EntitySystemWrapper<EntityEventSystem>
{
    public static class Wrapable extends EntitySystemWrapable<EntityEventSystem>
    {
        @Override
        public void update(float deltaTime) {
            ImmutableArray<Entity> entites =
                    getEngine().getEntitiesFor(Family.all(EntityCallbackReceiver.Wrapable.class).get());

            for(Entity entity : entites)
            {
                EntityCallbackReceiver eventComp = EntityCallbackReceiver.mapper.get(entity).getWrapper();
                eventComp.onUpdate(deltaTime);
            }
        }
    }



    @Override
    protected EntitySystemWrapable<EntityEventSystem> getWrappable()
    {
        return new Wrapable();
    }
}

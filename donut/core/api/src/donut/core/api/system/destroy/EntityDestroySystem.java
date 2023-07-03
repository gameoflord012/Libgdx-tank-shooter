package donut.core.api.system.destroy;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;

import donut.core.api.system.EntitySystemWrapable;
import donut.core.api.system.EntitySystemWrapper;

public class EntityDestroySystem extends EntitySystemWrapper<EntityDestroySystem>
{
    public static class Wrapable extends EntitySystemWrapable<EntityDestroySystem>
    {
        @Override
        public void update(float deltaTime) {
            super.update(deltaTime);

            for(Entity entity : getEngine().getEntitiesFor(Family.all(Destroyer.Wrapable.class).get()))
            {
                getEngine().removeEntity(entity);
            }
        }
    }


    @Override
    protected EntitySystemWrapable<EntityDestroySystem> getWrappable()
    {
        return new Wrapable();
    }
}

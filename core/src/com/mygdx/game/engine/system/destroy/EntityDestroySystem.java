package com.mygdx.game.engine.system.destroy;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;

public class EntityDestroySystem extends EntitySystem
{
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        for(Entity entity : getEngine().getEntitiesFor(Family.all(Destroyer.class).get()))
        {
            getEngine().removeEntity(entity);
        }
    }
}

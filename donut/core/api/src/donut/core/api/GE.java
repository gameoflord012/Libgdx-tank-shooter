package donut.core.api;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import donut.core.api.system.EntitySystemWrapper;
import donut.core.api.system.debug.DebugSystem;
import donut.core.api.system.destroy.EntityDestroySystem;
import donut.core.api.system.event.EntityEventSystem;
import donut.core.api.system.input.InputSystem;
import donut.core.api.system.physic.PhysicSystem;
import donut.core.api.system.render.RenderSystem;
import donut.core.api.utility.BaseWrapper;
import donut.core.api.utility.IWrapable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GE extends BaseWrapper<GE, GE.Wrappable>
{
    public GameEntity[] getEntitiesFor(Family family)
    {
        List<GameEntity> gameEntities = new ArrayList<>();
        for(Entity entity : wrapable.getEntitiesFor(family))
        {
            gameEntities.add(((GameEntity.Wrapable)entity).getWrapper());
        }

        return gameEntities.toArray(new GameEntity[0]);
    }

    public class Wrappable extends Engine implements IWrapable<GE>
    {
        private GE wrapper;
        @Override
        public void setWrapper(GE wrapper) {
            this.wrapper = wrapper;
        }

        @Override
        public GE getWrapper() {
            return wrapper;
        }
    }

    @Override
    protected Wrappable getWrappable() {
        return new Wrappable();
    }

    Map<String, EntitySystemWrapper<?>> systemMapper = new HashMap<>();

    private void addSystem(EntitySystemWrapper<?> systemWrapper)
    {
        systemMapper.put(systemWrapper.getClass().toString(), systemWrapper);
        wrapable.addSystem(systemWrapper.wrapable);
    }

    public GE()
    {
        super();

        addSystem(new InputSystem());
        addSystem(new PhysicSystem());
        addSystem(new EntityEventSystem());
        addSystem(new EntityDestroySystem());
        addSystem(new RenderSystem());
        addSystem(new DebugSystem());
    }

    public void addEntity(GameEntity entity)
    {
        wrapable.addEntity(entity.wrapable);
        entity.onGameEntityAdded(this);
    }

    public void update(float deltatime)
    {
        wrapable.update(deltatime);
    }

    public <T extends EntitySystemWrapper<T>> T getSystem(Class<T> systemClass)
    {
        return (T)systemMapper.get(systemClass.toString());
    }
}

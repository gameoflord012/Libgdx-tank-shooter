package core;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.system.EntitySystemWrapper;
import core.system.debug.DebugSystem;
import core.system.destroy.EntityDestroySystem;
import core.system.event.EntityEventSystem;
import core.system.input.InputSystem;
import core.system.physic.PhysicSystem;
import core.system.render.RenderSystem;
import utility.BaseWrapper;
import utility.IWrapable;

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
        addSystem(new DebugSystem());
        addSystem(new RenderSystem());
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

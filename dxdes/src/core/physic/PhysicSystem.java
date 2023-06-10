package core.physic;

import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import core.system.EntitySystemWrapable;
import core.system.EntitySystemWrapper;

public class PhysicSystem extends EntitySystemWrapper<PhysicSystem>
{
    public static class Wrapable extends EntitySystemWrapable<PhysicSystem>
    {
        @Override
        public void update(float deltaTime)
        {
            getWrapper().getWorld().step(deltaTime, 3, 3);
        }
    }

    @Override
    protected EntitySystemWrapable<PhysicSystem> getWrappable() {
        return new Wrapable();
    }

    final World world;

    public PhysicSystem()
    {
        this.world = new World(new Vector2(0, 0), true);
    }

    public World getWorld()
    {
        return world;
    }

    public PhysicBody getPhysicBody(BodyDef def)
    {
        return new PhysicBody(world, def);
    }


}

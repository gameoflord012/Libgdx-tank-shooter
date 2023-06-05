package systems.physic;

import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import systems.EntitySystemWrapper;

public class PhysicSystem extends EntitySystemWrapper
{
    public static Family family = Family.all(PhysicBody.Component.class).get();
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

    @Override
    public void update(float deltaTime)
    {
        world.step(deltaTime, 3, 3);
    }
}

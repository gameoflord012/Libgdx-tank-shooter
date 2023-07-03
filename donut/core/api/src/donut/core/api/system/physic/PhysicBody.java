package donut.core.api.system.physic;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import donut.core.api.component.ComponentWrapable;
import donut.core.api.component.ComponentWrapper;

public class PhysicBody extends ComponentWrapper<PhysicBody>
{
    @Override
    protected ComponentWrapable<PhysicBody> getWrappable() {
        return new Wrapable();
    }

    public static class Wrapable extends ComponentWrapable<PhysicBody>
    {

    }

    public final World world;
    public final Body body;
    protected PhysicBody(World world, BodyDef def)
    {
        this.world = world;
        this.body = world.createBody(def);
    }
}

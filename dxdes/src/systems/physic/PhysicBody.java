package systems.physic;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import core.ComponentWrapper;

public class PhysicBody extends ComponentWrapper
{
    public static ComponentMapper<ComponentWrapper.Component> mapper =
            ComponentMapper.getFor(ComponentWrapper.Component.class);

    public final World world;
    public final Body body;
    protected PhysicBody(World world, BodyDef def)
    {
        this.world = world;
        this.body = world.createBody(def);
    }
}

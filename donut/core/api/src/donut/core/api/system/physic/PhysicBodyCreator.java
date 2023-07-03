package donut.core.api.system.physic;

import com.badlogic.gdx.physics.box2d.BodyDef;

import donut.core.api.GameEntity;
import donut.core.api.component.ComponentCreator;

public class PhysicBodyCreator extends ComponentCreator<PhysicBody, PhysicSystem>
{
    @Override
    public PhysicBody createDefault(PhysicSystem system, GameEntity entity)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(entity.transform.px, entity.transform.py);

        return new PhysicBody(system.world, bodyDef);
    }

    @Override
    public Class<PhysicSystem> getSystemClass()
    {
        return PhysicSystem.class;
    }

    @Override
    public Class<PhysicBody> getComponentClass()
    {
        return PhysicBody.class;
    }
}

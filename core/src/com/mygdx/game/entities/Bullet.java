package com.mygdx.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.game.Core;

import core.GameEntity;
import core.system.event.EntityCallbackReceiver;
import core.system.event.IUpdateCallback;
import core.system.physic.PhysicBody;

public class Bullet extends GameEntity implements IUpdateCallback
{
    PhysicBody physicBody;
    @Override
    public void onUpdate(float delta)
    {
        if(!Core.getInstance().isPointScreen(physicBody.body.getPosition().x, physicBody.body.getPosition().y))
        {
            physicBody.body.setActive(false);
        }
    }

    public Bullet(Vector2 position, float angle)
    {
        add(new EntityCallbackReceiver().setUpdater(this));

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;

        def.position.set(position);
        def.angle = angle;

        physicBody = Core.physic().getPhysicBody(def);
        add(physicBody);

        FixtureDef fixture = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.set(new float[]{
                0, 4,
                2, -2,
                -2, -2
        });
        fixture.shape = shape;
        fixture.isSensor = true;
        physicBody.body.createFixture(fixture);
        physicBody.body.setLinearVelocity(physicBody.body.getWorldVector(new Vector2(0, 50)));
    }
}
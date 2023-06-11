package com.mygdx.game.entities;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.game.Core;

import core.GameEntity;
import core.system.debug.Debugger;
import core.system.event.EntityCallbackReceiver;
import core.system.event.IUpdateCallback;
import core.system.physic.PhysicBody;

public class Tank extends GameEntity implements IUpdateCallback
{
    PhysicBody pBody;
    int controlKey;

    public Tank(int controlKey)
    {
        this.controlKey = controlKey;
        add(new EntityCallbackReceiver().setUpdater(this));
        add(new Debugger());

        CreateBodies();

        Core.input().addInputProcessor(new InputAdapter()
        {
            @Override
            public boolean keyDown(int keycode) {
                if(keycode == Tank.this.controlKey)
                {
                    pBody.body.setAngularVelocity(3.5f);

                    Bullet bullet = new Bullet(
                        pBody.body.getWorldPoint(new Vector2(0, 15)),
                        pBody.body.getAngle());

                    return true;
                }

                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                if(keycode == Tank.this.controlKey)
                {
                    pBody.body.setAngularVelocity(-3.5f);

                    return true;
                }

                return false;
            }
        });
    }
    private void CreateBodies() {
        BodyDef dynamic = new BodyDef();
        dynamic.type = BodyDef.BodyType.DynamicBody;
        dynamic.position.set(0, 0);

        pBody = Core.physic().getPhysicBody(dynamic);
        add(pBody);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(8,10);
        FixtureDef fixture = new FixtureDef();
        fixture.shape = shape;
        pBody.body.createFixture(fixture);

        shape.setAsBox(3 ,8, new Vector2(0, 5), 0);
        fixture.shape = shape;
        pBody.body.createFixture(fixture);

        shape.dispose();

        pBody.body.setAngularVelocity(-3.5f);
    }

    @Override
    public void onUpdate(float delta)
    {
        pBody.body.setLinearVelocity(pBody.body.getWorldVector(new Vector2(0, 30)));

        transform.px = pBody.body.getPosition().x;
        transform.py = pBody.body.getPosition().y;
    }
}

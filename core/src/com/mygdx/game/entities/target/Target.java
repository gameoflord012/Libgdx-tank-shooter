package com.mygdx.game.entities.target;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.game.Core;

import java.util.HashSet;
import java.util.Set;

import core.GameEntity;
import core.event.EntityCallbackReceiver;
import core.event.IUpdateCallback;

public class Target extends GameEntity implements IUpdateCallback
{
    public abstract static class Event
    {
        public abstract void onTargetGoHeaven();
    }

    private Set<Event> liseners = new HashSet<Event>();

    public void addTargetEventLisener (Event lisener)
    {
        liseners.add(lisener);
    }

    public Body body;
    private boolean active;

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public Target()
    {
        add(new EntityCallbackReceiver().setUpdater(this));
        CreateBodies();

        active = true;

        Core.physic().addContactLisener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                if(
                        contact.getFixtureA().getBody().getUserData()== Target.this||
                        contact.getFixtureB().getBody().getUserData() == Target.this)
                {
                    active = false;
                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
    }

    @Override
    public void onUpdate(float delta)
    {
        if(active && !body.isActive())
        {
            body.setActive(true);
        }

        if(!active && body.isActive())
        {
            body.setActive(false);

            for(Event lisener : liseners)
            {
                if(lisener != null)
                    lisener.onTargetGoHeaven();
            }
        }
    }

    public void setPosition(float px, float py)
    {
        body.setTransform(px, py, 0);
    }

    private void CreateBodies() {
        BodyDef dynamic = new BodyDef();
        dynamic.type = BodyDef.BodyType.StaticBody;
        dynamic.position.set(0, 0);

        this.body = Core.physic().getWorld().createBody(dynamic);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(5,5);
        FixtureDef fixture = new FixtureDef();
        fixture.shape = shape;
        body.createFixture(fixture);

        body.setUserData(this);

        shape.dispose();
    }

    @Override
    public void Destroy()
    {
        super.Destroy();

        if(body != null)
        {
            Core.physic().getWorld().destroyBody(body);
            body = null;
        }
    }
}

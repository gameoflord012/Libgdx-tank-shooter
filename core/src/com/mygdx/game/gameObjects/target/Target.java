package com.mygdx.game.gameObjects.target;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.game.WorldLisenerRegister;
import com.mygdx.game.gameObjects.GameEntity;

public class Target extends GameEntity
{
    public abstract static class TargetEvent
    {
        public abstract void onTargetGoHeaven();
    }

    public TargetEvent lisener;

    public Body body;
    WorldLisenerRegister worldLisenerRegister;
    boolean alive;

    public Target(WorldLisenerRegister worldLisenerRegister)
    {
        this.worldLisenerRegister = worldLisenerRegister;
        CreateBodies();

        alive = true;

        worldLisenerRegister.addContactLisener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                if(
                        contact.getFixtureA().getBody().getUserData()== Target.this||
                        contact.getFixtureB().getBody().getUserData() == Target.this)
                {
                    alive = false;
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

    public void setPosition(float px, float py)
    {
        body.setTransform(px, py, 0);
    }

    private void CreateBodies() {
        BodyDef dynamic = new BodyDef();
        dynamic.type = BodyDef.BodyType.StaticBody;
        dynamic.position.set(0, 0);

        body = worldLisenerRegister.getWorld().createBody(dynamic);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(5,5);
        FixtureDef fixture = new FixtureDef();
        fixture.shape = shape;
        body.createFixture(fixture);

        body.setUserData(this);

        shape.dispose();
    }

    @Override
    public void update(float delta)
    {
        if(!alive && body.isActive())
        {
            body.setActive(false);

            if(lisener != null)
                lisener.onTargetGoHeaven();
        }
    }
}

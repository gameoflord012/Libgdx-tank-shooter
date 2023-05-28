package com.mygdx.game.gameObjects;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Target extends GameEntity
{
    public Body body;
    World world;

    boolean alive;

    public Target(final World world)
    {
        this.world = world;
        CreateBodies();

        alive = true;

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                if(
                        contact.getFixtureA().getBody().getUserData() == "Target.class" ||
                        contact.getFixtureB().getBody().getUserData() == "Target.class")
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

    private void CreateBodies() {
        BodyDef dynamic = new BodyDef();
        dynamic.type = BodyDef.BodyType.StaticBody;
        dynamic.position.set(0, 0);

        body = world.createBody(dynamic);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(5,5);
        FixtureDef fixture = new FixtureDef();
        fixture.shape = shape;
        body.createFixture(fixture);

        body.setUserData("Target.class");

        shape.dispose();
    }

    @Override
    public void update(float delta) {
        if(!alive && body.isActive())
        {
            body.setActive(false);
        }
    }
}

package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

import java.util.HashSet;
import java.util.Set;

public class WorldLisenerRegister
{
    private World world;
    Set<ContactListener> liseners = new HashSet<ContactListener>();

    public World getWorld()
    {
        return world;
    }

    public WorldLisenerRegister(World world)
    {
        this.world = world;

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                for(ContactListener lisener : liseners)
                {
                    lisener.beginContact(contact);
                }
            }

            @Override
            public void endContact(Contact contact)
            {
                for(ContactListener lisener : liseners)
                {
                    lisener.endContact(contact);
                }
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold)
            {
                for(ContactListener lisener : liseners)
                {
                    lisener.preSolve(contact, oldManifold);
                }
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
                for(ContactListener lisener : liseners)
                {
                    lisener.postSolve(contact, impulse);
                }
            }
        });
    }

    public void addContactLisener(ContactListener contactListener)
    {
        liseners.add(contactListener);
    }
}
